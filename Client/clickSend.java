
package Client;

import Mysql.Message;
import java.awt.event.ActionEvent;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import javax.swing.*;


public class clickSend extends AbstractAction{
    public JPanel panel;
    public int tab;
    public Client client;
    public String nickon;
    public clickSend(JPanel panel, Client client)
    {
        this.panel = panel;
        this.tab = tab;
        this.client = client;
        this.nickon = client.nickon;
    }
    @Override
    public void actionPerformed(ActionEvent ex) {
            JPanel panel2 = (JPanel) panel.getComponent(1);
            JTextField chat = (JTextField) panel2.getComponent(0);
            String loichat = chat.getText();
            Vector text = new Vector();
            text.add(loichat);
            //Send sang nguoi nhan
            client.sendMSG(new Message("send",client.nickName,text,client.nickChat()));
            //Dua len tab
            Enumeration e = client.list.keys();
            String name = null;
            while(e.hasMoreElements()){
                            name=(String) e.nextElement();
                            //System.out.println(name);
                            if(name.equals(nickon))  client.list.get(name).append("Tôi:\t"+loichat+"\n");
                    }
            //Khung chat null
            chat.setText(null);         
    }
    
}
