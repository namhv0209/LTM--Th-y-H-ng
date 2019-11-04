package Sever;

import Mysql.Message;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;

public class Server extends javax.swing.JFrame {
    public ClientConnect sk;
    public ServerSocket server;
    public Hashtable<String,ClientConnect> listUser;
    
    public Server() {
        this.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                try {
                        server.close();
                        System.exit(0);
                } catch (IOException e1) {
                        e1.printStackTrace();
                }
            }
        });
        initComponents();
        setVisible(true);
    }
    public void go()
    {
        try {
            //Maybe...
            listUser = new Hashtable<String,ClientConnect>();
            server = new ServerSocket(2134);
            user.append("Máy chủ được khởi động\n");
            while(true)
            {
                user.append("Maybe...\n");
                try {
                    Socket client = server.accept();
                    new ClientConnect(this, client);
                } catch (Exception e) {
                }

            }

        } catch (Exception e) {
                user.append("Không thể khởi động máy chủ\n");

        }
    }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        user = new javax.swing.JTextArea();
        jPanel1 = new javax.swing.JPanel();
        Close = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Server");

        user.setEditable(false);
        user.setColumns(20);
        user.setRows(5);
        user.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        jScrollPane1.setViewportView(user);

        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        Close.setText("Close");
        Close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CloseActionPerformed(evt);
            }
        });
        jPanel1.add(Close);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    //Nut Close
    private void CloseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CloseActionPerformed
        try {
            server.close();
        } catch (Exception ex) {
            
        }
        System.exit(0);
    }//GEN-LAST:event_CloseActionPerformed
    
   
    //Gui online ve cac Client
    public void sendAllUpdate(){
        Enumeration e = listUser.keys();
        String name=null;
        while(e.hasMoreElements()){
            name=(String) e.nextElement();
            //System.out.println(name);
            listUser.get(name).sendMSG(new Message("Online","SERVER",getAllName(),"CLIEN"));
        }
    }
    
    //Gui tin nhan ve Client tuong ung
    public void sendOne(Message msg)
    {
        Enumeration e = listUser.keys();
        String name = null;
        while(e.hasMoreElements()){
            name=(String) e.nextElement();
            //System.out.println(name);
            if(msg.recipient.equals(name)) listUser.get(name).sendMSG(new Message("send",msg.sender,msg.content,msg.recipient));
        }
    }
    public void sendNewChat(Message msg, Vector lschat)
    {
        Enumeration e = listUser.keys();
        String name = null;
        while(e.hasMoreElements()){
            name=(String) e.nextElement();
            //System.out.println(name);
            if(msg.sender.equals(name)) listUser.get(name).sendMSG(new Message("newchat","SERVER",lschat,msg.recipient));
        }
    }
    //Send Logout
    public void sendLogout(Message msg)
    {
        Enumeration e = listUser.keys();
        String name = null;
        while(e.hasMoreElements()){
                name=(String) e.nextElement();
                //System.out.println(name);
                if(msg.sender.equals(name)) listUser.get(name).sendMSG(new Message("logout","SERVER",null,"CLIENT"));
        }
    }
    //Lay Danh Sach Client dang ket noi
   public Vector getAllName(){
            Enumeration e = listUser.keys();
            int size = listUser.size();
            Vector allname = new  Vector();
            int i =0;
            while(e.hasMoreElements()){
                    allname.add(e.nextElement().toString());
                    i++;
            }
            return allname;
	}
   
    public boolean check(String namekt)
    {
         Enumeration er = listUser.keys();
            String name = null;
            while(er.hasMoreElements()){
                    name=(String) er.nextElement();
                    if(name.equals(namekt)) return false;
		}
            return true;
    }    
    public static void main(String args[]) {
        new Server().go(); 
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Close;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTextArea user;
    // End of variables declaration//GEN-END:variables
}
