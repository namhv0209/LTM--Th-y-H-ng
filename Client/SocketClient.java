
package Client;

import Mysql.Message;
import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;


public class SocketClient implements Runnable{
    private Socket socket;
    public ObjectInputStream in;
    public Client client;
    public boolean run;
    public Thread thread;

    public SocketClient(Client client,ObjectInputStream in){
		//run=true;
		this.client=client;
		this.in=in;
		this.start();
	}
     public void start()
        {   
            run = true;
            thread = new Thread(this);
            thread.start();
        }
    @Override
    public void run()
    {   
        //Luon nhan thong tin tu sever
        while(run)
        {
            Message msg = client.getMSG();
            XuLy(msg);
        }
    }
    
    
    public void XuLy(Message msg)
    {
        //Xu Ly Online
        if(msg.type.equals("Online"))
        {   
            client.tbonline.removeAll();
            Vector cols = new Vector();
            cols.add("DS Online");
            
            DefaultTableModel tabonline = new DefaultTableModel(cols,0);
            client.tbonline.setModel(tabonline);
            
            for(int i = 0 ; i < msg.content.size()  ; i++)
            {
               if(!client.nickName.equals(msg.content.get(i)))
               {
                    String[] row = {msg.content.get(i).toString()};
                    tabonline.addRow(row);
               }
            }
        }
        
        //Xu ly tin nhan ve
        if(msg.type.equals("newchat"))
        {
            client.nickon = msg.recipient;
            client.addNewTab(msg.recipient, msg.content);
            client.ChuyenPanel(client.Pchat,client.Pchat2);
        }
        
        //Xu Ly tin nhan gui ve
        if(msg.type.equals("send"))
        {
            if(!client.check(msg.sender))client.list.get(msg.sender).append(msg.sender+":\t"+msg.content.get(0).toString()+"\n"); 
            else client.sendMSG(new Message("newchat",msg.recipient, null, msg.sender));
            //}
         }
        
        //Xu ly logout
        if(msg.type.equals("logout"))
        {
            run = false;
            try {
                 in.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
             
        }
        
        //Xu Ly Reg
        
    }

}
