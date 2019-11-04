
package Mysql;


public class Login extends Connectsql{
    
    public Login() {
          try {
            conn = Connectsql.getConnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public boolean Login(String username, String password){
        boolean check = false;
        String sql_login = "SELECT *FROM login where username = ? and password = ?";
        try {
            stmt = conn.prepareStatement(sql_login);
            stmt.setString(1,username);
            stmt.setString(2,password);
            rs = stmt.executeQuery();
            if(rs!=null&& rs.next()){
                check = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  check;
    }
    public boolean checkname(String name)
    {
        String check = "SELECT *FROM login where username = ?";
        try {
            stmt =conn.prepareStatement(check);
            stmt.setString(1, name);
            rs = stmt.executeQuery();
            if(rs!=null&& rs.next()){
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Sai check Name");
        }
        return true;
    }
    
    public boolean Reg(String name, String pass)
    {
        String sql_add ="INSERT INTO login(username, password) VALUES (?,?)";
        try {
            stmt = conn.prepareStatement(sql_add);
            stmt.setString(1,name);
            stmt.setString(2,pass);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
             e.printStackTrace();
             System.out.println("Loi Reg");
        }
        return false;
    }
  
}
