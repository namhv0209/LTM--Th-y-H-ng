
package Mysql;

import Mysql.*;
import java.util.*;


public class checkLs extends Connectsql{
    String sql_show ="SELECT *FROM lschat WHERE (id1= ? and id2 = ?) or (id1 =? and id2 =?)";
    
    public checkLs()
    {
        try {
            conn = Connectsql.getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public int LayId(String username)
    {
        int id = -1;
        String sql_id = "SELECT *FROM login where username = ?";
        try {
            stmt = conn.prepareStatement(sql_id);
            stmt.setString(1,username);
           
            rs = stmt.executeQuery();
            if(rs!=null&& rs.next()){
                id = rs.getInt("id");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Loi o Lay ID");
        }
        return id;
    }
    
    public boolean setDb(LsChat ls)
        {
            String sql_add ="INSERT INTO lschat(id1,id2,content,idsender) VALUES (?,?,?,?)";
            try {
                stmt = conn.prepareStatement(sql_add);
                stmt.setInt(1,ls.getId1());
                stmt.setInt(2,ls.getId2());
                stmt.setString(3,ls.getNoidung());
                stmt.setInt(4,ls.getSender());
                //Thuc Hien
                stmt.executeUpdate();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Loi setDB");
            }
            return false;
        }
    public Vector showLs(int id1, int id2)
        {
            Vector data = new Vector();
            try {
                stmt = conn.prepareStatement(sql_show);
                stmt.setInt(1,id1);
                stmt.setInt(2,id2);
                stmt.setInt(3,id2);
                stmt.setInt(4,id1);
                rs = stmt.executeQuery();
                if(rs!=null){
                    while(rs.next())
                    {
                        LsChat ls = new LsChat();
                        ls.setStt(rs.getInt("stt"));
                        ls.setId1(rs.getInt("id1"));
                        ls.setId2(rs.getInt("id2"));
                        ls.setNoidung(rs.getString("content"));
                        ls.setSender(rs.getInt("idsender"));
                        data.add(ls);
                    }
                }
            } catch (Exception e) {
                System.out.println("Loi ShowLs");
                e.printStackTrace();
            }
        return data;  
        }
}
