
package Mysql;

import com.mysql.jdbc.Connection;
import java.sql.*;

public class Connectsql {
    protected static Connection conn;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    
    public static Connection getConnect()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/btl","root","");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }
}
