
package Client;

import Mysql.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

public class Client extends javax.swing.JFrame {
    private Socket client;
    public String nickName;
    public int id;
    public String nickon;
    public ObjectInputStream in;
    public ObjectOutputStream out;
    public static Hashtable<String, JTextArea> list = new Hashtable<>();
    int numTabs=0;
    checkLs check = new checkLs();
   
    public Client() {
         this.addWindowListener(new WindowAdapter(){
                    @Override
                    public void windowClosing(WindowEvent e){
                            try {
                                    sendMSG(new Message("logout",nickName,null,"SERVER"));
                                    System.exit(0);
                            } catch (Exception e1) {
                                    e1.printStackTrace();
                            }
                    }
            });
        initComponents();
        go();
    }
    
    
     private void go() {
		try {
			client = new Socket("localhost",2134);
			out = new ObjectOutputStream(client.getOutputStream());
                        out.flush();
                        in = new ObjectInputStream(client.getInputStream());
		} catch (Exception e) {
			JOptionPane.showMessageDialog(this,"Lỗi kết nối, Server chưa mở.","Message Dialog",JOptionPane.WARNING_MESSAGE);
			System.exit(0);
		}
	}
    
    public void ChuyenPanel(JPanel p1,JPanel p2){
        p1.removeAll();
        p1.add(p2);
        p1.repaint();
        p1.revalidate();
    }
    
    //Tao 1 Tab moi
    public void addNewTab(String nick,Vector lschat) {
        int index = numTabs;
        //ImageIcon icon = new ImageIcon("Img/add1.png");
        tabbedPane.add(createJPanel(lschat),nick,index);
        //tabbedPane.setIconAt(index, icon);
        tabbedPane.setTabComponentAt(index, new TabChat(this));
        tabbedPane.setSelectedIndex(index);
        numTabs++;
    }
    
    //Lay Tieu De
    public String nickChat()
    {
        int index = tabbedPane.getSelectedIndex();
        String td = tabbedPane.getTitleAt(index);
        return td;
    }
    
    //Tao Pannel trong Tab
    public JPanel createJPanel(Vector lschat) {
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
        panel.setLayout(boxLayout);
        JTextArea ta = createTextArea(10,30,lschat);
        list.put(nickon,ta);
        //Them cac thanh phan vao Panel
        panel.add(new JScrollPane(ta),0);
        panel.add(createPanelSend(),1);
        
        //Xu ly su kien nut button
        JPanel panel2 = (JPanel) panel.getComponent(1);
        JButton btn = (JButton) panel2.getComponent(1);
        btn.addActionListener(new clickSend(panel,this));
        
        return panel;
    }
    
    //Tao text load van ban
    public JTextArea createTextArea(int row, int col,Vector lschat) {
        JTextArea ta = new JTextArea(row, col);
        if(!lschat.isEmpty())
        {
            for(int i =0; i<lschat.size() ; i++)
            {
                LsChat ls = new LsChat();
                ls = (LsChat) lschat.get(i);
                if(ls.getSender() == id)
                {
                    ta.append("Tôi:\t"+ls.getNoidung()+"\n");
                }
                else
                {   
                    ta.append(nickon+"\t"+ls.getNoidung()+"\n");
                }
            }
        }
        ta.setForeground(Color.BLACK);
        ta.setWrapStyleWord(true);
        ta.setLineWrap(true);
        return ta;
    }
    
    //Tao Panel chua O nhap text va nut Send
     public JPanel createPanelSend()
    {   
        JPanel panel = new JPanel();
        BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.X_AXIS);
        panel.setLayout(boxLayout);
        JTextField text = new JTextField(15);
        JButton btn = new JButton("Send");
        panel.add(text,0);
        panel.add(btn,1);
        
        return panel;
    }
 
    
 
    public void removeTab(int index) {
        tabbedPane.remove(index);
        numTabs--;
        if(numTabs < 0)  ChuyenPanel(Pchat,Pchat1);
        }
    
     //Gui data ve Client
    public synchronized void sendMSG(Message mgs){
                    try {   
			out.writeObject(mgs);
                        out.flush();
                    } catch (Exception e) {
			e.printStackTrace();
                        System.out.println("Loi send Client");
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
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PMain = new javax.swing.JPanel();
        PLogin = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Img/so-kieu.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        PLg = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Img/bg.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel1 = new javax.swing.JLabel();
        txtuser = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtpass = new javax.swing.JPasswordField();
        btnreg = new javax.swing.JButton();
        btnlogin = new javax.swing.JButton();
        ThongBaofail = new javax.swing.JLabel();
        PHome = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Img/bg.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jScrollPane1 = new javax.swing.JScrollPane();
        tbonline = new javax.swing.JTable();
        Pchat = new javax.swing.JPanel();
        Pchat1 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Img/so-kieu-2.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        Pchat2 = new javax.swing.JPanel();
        tabbedPane = new javax.swing.JTabbedPane();
        Pchat3 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Img/qh.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtid = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtusername = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        Logout = new javax.swing.JButton();
        PHay = new javax.swing.JPanel();
        P1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        P2 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        P3 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        P0 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        PReg = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Img/so-kieu-3.jpg");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        Preg2 = new javax.swing.JPanel(){
            ImageIcon icon = new ImageIcon("src/Img/bg.png");
            public void paintComponent(Graphics g){
                Dimension d = getSize();
                g.drawImage(icon.getImage(), 0, 0, d.width, d.height, null);
                setOpaque(false);
                super.paintComponent(g);
            }
        };
        jLabel7 = new javax.swing.JLabel();
        txtruser = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtrpass = new javax.swing.JPasswordField();
        jLabel9 = new javax.swing.JLabel();
        txtrpass2 = new javax.swing.JPasswordField();
        rlogin = new javax.swing.JButton();
        btnregg = new javax.swing.JButton();
        ThongBaoReg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        PMain.setLayout(new java.awt.CardLayout());

        PLg.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(102, 102, 102), new java.awt.Color(153, 153, 153), null, null));
        PLg.setLayout(new java.awt.GridLayout(3, 2));

        jLabel1.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 14)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/user_male_16.png"))); // NOI18N
        jLabel1.setText("Username");
        PLg.add(jLabel1);

        txtuser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtuserMouseClicked(evt);
            }
        });
        PLg.add(txtuser);

        jLabel2.setFont(new java.awt.Font("Bodoni MT Condensed", 3, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/password.png"))); // NOI18N
        jLabel2.setText("Password");
        PLg.add(jLabel2);

        txtpass.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtpassMouseClicked(evt);
            }
        });
        PLg.add(txtpass);

        btnreg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/add1.png"))); // NOI18N
        btnreg.setText("Register");
        btnreg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnregActionPerformed(evt);
            }
        });
        PLg.add(btnreg);

        btnlogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login.png"))); // NOI18N
        btnlogin.setText("Login");
        btnlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnloginActionPerformed(evt);
            }
        });
        PLg.add(btnlogin);

        ThongBaofail.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N

        javax.swing.GroupLayout PLoginLayout = new javax.swing.GroupLayout(PLogin);
        PLogin.setLayout(PLoginLayout);
        PLoginLayout.setHorizontalGroup(
            PLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PLoginLayout.createSequentialGroup()
                .addContainerGap(388, Short.MAX_VALUE)
                .addComponent(ThongBaofail, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34))
            .addGroup(PLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PLoginLayout.createSequentialGroup()
                    .addContainerGap(346, Short.MAX_VALUE)
                    .addComponent(PLg, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        PLoginLayout.setVerticalGroup(
            PLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PLoginLayout.createSequentialGroup()
                .addContainerGap(332, Short.MAX_VALUE)
                .addComponent(ThongBaofail, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(PLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(PLoginLayout.createSequentialGroup()
                    .addGap(249, 249, 249)
                    .addComponent(PLg, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(41, Short.MAX_VALUE)))
        );

        PMain.add(PLogin, "card3");

        tbonline.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null},
                {null}
            },
            new String [] {
                "Online"
            }
        ));
        tbonline.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbonlineMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbonline);

        Pchat.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout Pchat1Layout = new javax.swing.GroupLayout(Pchat1);
        Pchat1.setLayout(Pchat1Layout);
        Pchat1Layout.setHorizontalGroup(
            Pchat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );
        Pchat1Layout.setVerticalGroup(
            Pchat1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        Pchat.add(Pchat1, "card2");

        javax.swing.GroupLayout Pchat2Layout = new javax.swing.GroupLayout(Pchat2);
        Pchat2.setLayout(Pchat2Layout);
        Pchat2Layout.setHorizontalGroup(
            Pchat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Pchat2Layout.createSequentialGroup()
                .addComponent(tabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Pchat2Layout.setVerticalGroup(
            Pchat2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabbedPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
        );

        Pchat.add(Pchat2, "card3");

        javax.swing.GroupLayout Pchat3Layout = new javax.swing.GroupLayout(Pchat3);
        Pchat3.setLayout(Pchat3Layout);
        Pchat3Layout.setHorizontalGroup(
            Pchat3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 414, Short.MAX_VALUE)
        );
        Pchat3Layout.setVerticalGroup(
            Pchat3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 218, Short.MAX_VALUE)
        );

        Pchat.add(Pchat3, "card4");

        jLabel4.setFont(new java.awt.Font("Gigi", 3, 27)); // NOI18N
        jLabel4.setText("Chat Online");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(218, 218, 218)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addContainerGap())
        );

        jPanel3.setLayout(new java.awt.GridLayout(2, 2));

        jLabel3.setFont(new java.awt.Font("Bradley Hand ITC", 2, 12)); // NOI18N
        jLabel3.setText("ID:");
        jPanel3.add(jLabel3);

        txtid.setEditable(false);
        txtid.setBackground(new java.awt.Color(204, 204, 204));
        txtid.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtid.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel3.add(txtid);

        jLabel5.setFont(new java.awt.Font("Bradley Hand ITC", 2, 12)); // NOI18N
        jLabel5.setText("Username: ");
        jPanel3.add(jLabel5);

        txtusername.setEditable(false);
        txtusername.setBackground(new java.awt.Color(204, 204, 204));
        txtusername.setFont(new java.awt.Font("Tahoma", 1, 10)); // NOI18N
        txtusername.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        jPanel3.add(txtusername);

        jLabel6.setFont(new java.awt.Font("Sitka Text", 3, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("#Nhom 14");

        Logout.setFont(new java.awt.Font("Californian FB", 3, 12)); // NOI18N
        Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/delete.png"))); // NOI18N
        Logout.setText("LogOut");
        Logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LogoutActionPerformed(evt);
            }
        });

        PHay.setLayout(new java.awt.CardLayout());

        P1.setBackground(new java.awt.Color(247, 243, 247));

        jLabel12.setFont(new java.awt.Font("Times New Roman", 3, 11)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 51));
        jLabel12.setText("");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 3, 11)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 0, 51));
        jLabel13.setText("");

        javax.swing.GroupLayout P1Layout = new javax.swing.GroupLayout(P1);
        P1.setLayout(P1Layout);
        P1Layout.setHorizontalGroup(
            P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P1Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jLabel12)
                .addGap(78, 78, 78))
            .addGroup(P1Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        P1Layout.setVerticalGroup(
            P1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addContainerGap())
        );

        PHay.add(P1, "card2");

        P2.setBackground(new java.awt.Color(247, 243, 247));

        jLabel14.setFont(new java.awt.Font("Times New Roman", 3, 11)); // NOI18N
        jLabel14.setText("");

        jLabel15.setFont(new java.awt.Font("Times New Roman", 0, 11)); // NOI18N
        jLabel15.setText("");

        javax.swing.GroupLayout P2Layout = new javax.swing.GroupLayout(P2);
        P2.setLayout(P2Layout);
        P2Layout.setHorizontalGroup(
            P2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P2Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jLabel14)
                .addContainerGap(136, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel15)
                .addGap(95, 95, 95))
        );
        P2Layout.setVerticalGroup(
            P2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PHay.add(P2, "card3");

        P3.setBackground(new java.awt.Color(247, 243, 247));

        jLabel16.setFont(new java.awt.Font("Times New Roman", 3, 11)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(0, 102, 102));
        jLabel16.setText("");

        jLabel17.setFont(new java.awt.Font("Times New Roman", 3, 11)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 102, 102));
        jLabel17.setText("");

        javax.swing.GroupLayout P3Layout = new javax.swing.GroupLayout(P3);
        P3.setLayout(P3Layout);
        P3Layout.setHorizontalGroup(
            P3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P3Layout.createSequentialGroup()
                .addContainerGap(162, Short.MAX_VALUE)
                .addComponent(jLabel17)
                .addGap(140, 140, 140))
            .addGroup(P3Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jLabel16)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        P3Layout.setVerticalGroup(
            P3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel17)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PHay.add(P3, "card4");

        P0.setBackground(new java.awt.Color(247, 243, 247));

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 0, 204));
        jLabel10.setText("");

        jLabel11.setFont(new java.awt.Font("Times New Roman", 3, 11)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(153, 0, 204));
        jLabel11.setText("");

        javax.swing.GroupLayout P0Layout = new javax.swing.GroupLayout(P0);
        P0.setLayout(P0Layout);
        P0Layout.setHorizontalGroup(
            P0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, P0Layout.createSequentialGroup()
                .addContainerGap(103, Short.MAX_VALUE)
                .addComponent(jLabel11)
                .addGap(64, 64, 64))
            .addGroup(P0Layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        P0Layout.setVerticalGroup(
            P0Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(P0Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        PHay.add(P0, "card6");

        javax.swing.GroupLayout PHomeLayout = new javax.swing.GroupLayout(PHome);
        PHome.setLayout(PHomeLayout);
        PHomeLayout.setHorizontalGroup(
            PHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PHomeLayout.createSequentialGroup()
                .addGroup(PHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(PHomeLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(PHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(PHomeLayout.createSequentialGroup()
                                .addComponent(PHay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(PHomeLayout.createSequentialGroup()
                                .addGroup(PHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(PHomeLayout.createSequentialGroup()
                                        .addComponent(Logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(10, 10, 10))
                                    .addGroup(PHomeLayout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PHomeLayout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)))
                                .addComponent(Pchat, javax.swing.GroupLayout.PREFERRED_SIZE, 414, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(8, 8, 8)))))
                .addGap(79, 79, 79))
        );
        PHomeLayout.setVerticalGroup(
            PHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(PHomeLayout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(PHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 50, Short.MAX_VALUE)
                    .addComponent(PHay, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE))
                .addGap(11, 11, 11)
                .addGroup(PHomeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Pchat, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(PHomeLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(Logout, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel6)))
                .addGap(43, 43, 43))
        );

        PMain.add(PHome, "card2");

        Preg2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, new java.awt.Color(51, 51, 51), new java.awt.Color(0, 0, 0), null, null));
        Preg2.setLayout(new java.awt.GridLayout(4, 2));

        jLabel7.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/user_male_16.png"))); // NOI18N
        jLabel7.setText(" Username:");
        Preg2.add(jLabel7);
        Preg2.add(txtruser);

        jLabel8.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/key-24.png"))); // NOI18N
        jLabel8.setText("Password:");
        Preg2.add(jLabel8);
        Preg2.add(txtrpass);

        jLabel9.setFont(new java.awt.Font("Monospaced", 0, 12)); // NOI18N
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/key-24.png"))); // NOI18N
        jLabel9.setText("R-Password:");
        Preg2.add(jLabel9);
        Preg2.add(txtrpass2);

        rlogin.setFont(new java.awt.Font("Times New Roman", 3, 12)); // NOI18N
        rlogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/login.png"))); // NOI18N
        rlogin.setText("Login");
        rlogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rloginActionPerformed(evt);
            }
        });
        Preg2.add(rlogin);

        btnregg.setFont(new java.awt.Font("Times New Roman", 3, 12)); // NOI18N
        btnregg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/add1.png"))); // NOI18N
        btnregg.setText("Reg");
        btnregg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnreggActionPerformed(evt);
            }
        });
        Preg2.add(btnregg);

        ThongBaoReg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout PRegLayout = new javax.swing.GroupLayout(PReg);
        PReg.setLayout(PRegLayout);
        PRegLayout.setHorizontalGroup(
            PRegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PRegLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(PRegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PRegLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(ThongBaoReg, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(Preg2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(293, 293, 293))
        );
        PRegLayout.setVerticalGroup(
            PRegLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PRegLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(Preg2, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(ThongBaoReg, javax.swing.GroupLayout.DEFAULT_SIZE, 2, Short.MAX_VALUE)
                .addGap(212, 212, 212))
        );

        PMain.add(PReg, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PMain, javax.swing.GroupLayout.PREFERRED_SIZE, 570, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(PMain, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnloginActionPerformed
        //Xu ly du lieu tu Login
        if(txtuser.getText().equals("")) ThongBaofail.setText("TK Null");
        else if(txtpass.getText().equals("")) ThongBaofail.setText("Mat Khau Null");
        else {
                Vector login = new Vector();
                login.add(txtuser.getText().trim());
                login.add(txtpass.getText().trim());
                sendMSG(new Message("login","CLIENT", login,"SEVER"));
                Message msg = getMSG();
                if(msg.type.equals("login"))
                {
                    if(msg.content.get(0).equals("false2"))
                        ThongBaofail.setText("Sai TK or MK");
                    else if(msg.content.get(0).equals("false1"))
                    {
                        ThongBaofail.setText("TK Đang Login");
                    }  
                    else {
                    id = Integer.parseInt(msg.recipient);
                    nickName = txtuser.getText().trim();
                    txtid.setText(msg.recipient);
                    txtusername.setText(nickName);
                    new SocketClient(this,in);
                    ChuyenPanel(PMain,PHome);
                    Play(); //Cai nay nghich thoi
                    }
                    
                }
                else System.out.println("Ahihi");
            }
    }//GEN-LAST:event_btnloginActionPerformed

    private void tbonlineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbonlineMouseClicked
        int row = tbonline.getSelectedRow(); 
        if(numTabs==0) ChuyenPanel(Pchat,Pchat2);
        if(row !=-1) nickon= tbonline.getValueAt(row, 0).toString().trim();
        if(check(nickon)) sendMSG(new Message("newchat", nickName,null, nickon));
        else JOptionPane.showMessageDialog(this,"Có Tab rồi");
    }//GEN-LAST:event_tbonlineMouseClicked

    private void txtuserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtuserMouseClicked
       txtuser.setText(null);
    }//GEN-LAST:event_txtuserMouseClicked

    private void txtpassMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtpassMouseClicked
        txtpass.setText(null);
    }//GEN-LAST:event_txtpassMouseClicked

    private void LogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LogoutActionPerformed
        sendMSG(new Message("logout",nickName,null,"SERVER"));
        System.exit(0);
    }//GEN-LAST:event_LogoutActionPerformed

    private void btnregActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnregActionPerformed
        ChuyenPanel(PMain,PReg);
    }//GEN-LAST:event_btnregActionPerformed

    private void rloginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rloginActionPerformed
        ChuyenPanel(PMain, PLogin);
    }//GEN-LAST:event_rloginActionPerformed

    private void btnreggActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnreggActionPerformed
      if(txtruser.getText().equals("")) ThongBaoReg.setText("TK Trống");
        else if(txtrpass.getText().equals("")) ThongBaoReg.setText("PassWord Null");
        else if(txtrpass2.getText().equals("")) ThongBaofail.setText("PassWord Null");
        else if(!txtrpass.getText().equals(txtrpass2.getText())) ThongBaoReg.setText("Pass Lỗi");
        else {
                Vector login = new Vector();
                login.add(txtruser.getText().trim());
                login.add(txtrpass.getText().trim());
                sendMSG(new Message("reg","CLIENT", login,"SEVER"));
                Message msg = getMSG();
                if(msg.type.equals("reg"))
                {
                   ThongBaoReg.setText(msg.content.get(0).toString());
                }
                else System.out.println("Ahihi");
        }
    }//GEN-LAST:event_btnreggActionPerformed
    
    public boolean check(String namekt)
    {
         Enumeration er = list.keys();
            String name = null;
            while(er.hasMoreElements()){
                    name=(String) er.nextElement();
                    if(name.equals(namekt)) return false;
		}
            return true;
    }
    
    public void Play()
    {
        Random rd = new Random();
        int a = rd.nextInt(4);
        switch(a)
        {
            case 0: ChuyenPanel(PHay, P0); break;
            case 1: ChuyenPanel(PHay, P1); break;
            case 2: ChuyenPanel(PHay, P2); break;
            case 3: ChuyenPanel(PHay, P3); break;
            //case 4: ChuyenPanel(PHay, P0); ChuyenPanel(Pchat,Pchat3); break;
            //case 5: ChuyenPanel(PHay, P1); ChuyenPanel(Pchat,Pchat3); break;
            //case 6: ChuyenPanel(PHay, P2); ChuyenPanel(Pchat,Pchat3); break;
            //case 7: ChuyenPanel(PHay, P3); ChuyenPanel(Pchat,Pchat3); break;
                default: break;
        }
    }
    public static void main(String args[]) {
        new Client().setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Logout;
    private javax.swing.JPanel P0;
    private javax.swing.JPanel P1;
    private javax.swing.JPanel P2;
    private javax.swing.JPanel P3;
    private javax.swing.JPanel PHay;
    private javax.swing.JPanel PHome;
    private javax.swing.JPanel PLg;
    private javax.swing.JPanel PLogin;
    private javax.swing.JPanel PMain;
    private javax.swing.JPanel PReg;
    public javax.swing.JPanel Pchat;
    public javax.swing.JPanel Pchat1;
    public javax.swing.JPanel Pchat2;
    private javax.swing.JPanel Pchat3;
    private javax.swing.JPanel Preg2;
    private javax.swing.JLabel ThongBaoReg;
    private javax.swing.JLabel ThongBaofail;
    private javax.swing.JButton btnlogin;
    private javax.swing.JButton btnreg;
    private javax.swing.JButton btnregg;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    public javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton rlogin;
    public javax.swing.JTabbedPane tabbedPane;
    public javax.swing.JTable tbonline;
    private javax.swing.JTextField txtid;
    private javax.swing.JPasswordField txtpass;
    private javax.swing.JPasswordField txtrpass;
    private javax.swing.JPasswordField txtrpass2;
    private javax.swing.JTextField txtruser;
    private javax.swing.JTextField txtuser;
    private javax.swing.JTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
