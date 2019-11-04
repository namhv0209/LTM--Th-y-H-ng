
package Sever;

import Mysql.Login;
import Mysql.LsChat;
import Mysql.Message;
import Mysql.checkLs;
import java.awt.AWTEventMulticaster;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;
import javafx.scene.control.TreeItem;

public class ClientConnect implements Runnable{
        public ServerSocket server;
        public Socket client;
        public ObjectInputStream in;
        public ObjectOutputStream out;
        public Server sv;
        private Thread thread;
        private boolean run;
        private String nickName;
        private String passWord;

        public ClientConnect(Server sv,Socket socket)
        {   
            this.sv = sv;
            this.client = socket;
            
            try {
                out = new ObjectOutputStream(socket.getOutputStream());
                out.flush();
                in = new ObjectInputStream(socket.getInputStream());
                this.start();
            } catch (Exception e) {
                
            }
            
        }
        //Tao Luong ket noi cho client
        public void start()
        {   
            run = true;
            thread = new Thread(this);
            thread.start();
        }
        
         @Override
        public void run() {
            String mgs = null;
            while(run)
            {
                Message msg = getMSG();
                XuLy(msg);
            }
        }
        
        //Xu ly du lieu Client dua len
        public void XuLy(Message msg)
        {   
            //Xu ly login
            if(msg.type.equals("login"))
                {  
                    Vector check = new Vector();
                    nickName = msg.content.get(0).toString();
                    passWord = msg.content.get(1).toString();
                    if(!sv.check(nickName))
                    {
                        check.add("false1");
                        check.add("TK đang Login");
                        sendMSG(new Message("login","Server",check,"Client"));
                    }
                    else if(!checkLogin(nickName,passWord))
                    {   
                        check.add("false2");
                        sendMSG(new Message("login","Server",check,"Client"));
                    }
                    else
                    {   
                         checkLs l = new checkLs();
                        int id1 = l.LayId(nickName);
                        check.add("true");
                        sv.user.append("Client:\t"+nickName+"-"+client+"\n");
                        sendMSG(new Message("login","SERVER",check,Integer.toString(id1)));
                        sv.listUser.put(nickName,this);
                        sv.sendAllUpdate();
                    }
                
                }
            
            //New Chat
            if(msg.type.equals("newchat"))
            {
                checkLs l = new checkLs();
                int id1 = l.LayId(msg.sender);
                int id2 = l.LayId(msg.recipient);
                Vector lschat = l.showLs(id1, id2);
                sv.sendNewChat(msg, lschat);
            }
            //Xu ly tin nhan den
            if(msg.type.equals("send"))
            {
                //sv.sendOne(msg);
                checkLs l = new checkLs();
                int id1 = l.LayId(msg.sender);
                int id2 = l.LayId(msg.recipient);
                //Luu vao database
                LsChat ls = new LsChat();
                ls.setNoidung(msg.content.get(0).toString());
                ls.setSender(id1);
                if(id1 < id2)
                {
                    ls.setId1(id1);
                    ls.setId2(id2);
                }
                else
                {
                    ls.setId1(id2);
                    ls.setId2(id1);
                }
                if(l.setDb(ls)) System.out.println("Da gui");
                else System.out.println("Loi");
                sv.sendOne(msg);
                
            }
            
            //Logout
            if(msg.type.equals("logout"))
            {   
                try {
                    sv.sendLogout(msg);
                    sv.listUser.remove(nickName);
                    sv.sendAllUpdate();
                    sv.user.append(nickName+":\tĐã thoát\n");
                    run = false;
                    in.close();
                    out.close();
                    client.close();
                } catch (Exception e) {
                }
            }
            
            if(msg.type.equals("reg"))
            {
                Login l = new Login();
                Vector tb = new Vector();
                if(!l.checkname(msg.content.get(0).toString())) 
                {
                    tb.add("Name đã có");
                    sendMSG(new Message("reg","Server",tb,"Client"));
                }
                else
                {
                    String name = msg.content.get(0).toString();
                    String pass = msg.content.get(1).toString();
                    if(l.Reg(name, pass))
                    {
                        tb.add("Đăng Ký Thành Công");
                        sendMSG(new Message("reg","Server",tb,"Client"));
                    }
                }
            }
        }
        //Gui data ve Client
        public synchronized void sendMSG(Message mgs){
                    try {   
			out.writeObject(mgs);
                        out.flush();
                    } catch (Exception e) {
                        System.out.println("Loi o day");
			e.printStackTrace();
                }
	}
        
        //Lay data tu client gui len
        public Message getMSG(){
		Message data=null;
		try {
			data=(Message) in.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;
	}
        
        
        //Check Login tu client gui len
        public boolean checkLogin(String user, String pass)
        {
            boolean check = false;
            Login l = new Login();
            if(l.Login(user, pass)) check = true;
            return check;
        }
    
}
