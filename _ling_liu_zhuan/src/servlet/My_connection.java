package servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class My_connection {  
    // table  
    public static final String TABLE_COLLECTION = "collection";  
    public static final String TABLE_LAND = "land";
    public static final String TABLE_USER = "user";
    public static final String TABLE_PIC = "land_image";
  
    // connect to MySql database  
    public static Connection getConnect() {  
        String url = "jdbc:mysql://localhost:3306/lin_liu_zhuan"; // 数据库的Url  
        Connection connecter = null;  
        try {  
        	Class.forName("com.mysql.jdbc.Driver"); // java反射，固定写法  
            connecter = (Connection) DriverManager.getConnection(url, "root", "123456");  
        } catch (ClassNotFoundException e) {  
            e.printStackTrace();
        } catch (SQLException e) {  
            System.out.println("SQLException: " + e.getMessage());  
            System.out.println("SQLState: " + e.getSQLState());  
            System.out.println("VendorError: " + e.getErrorCode());  
        }  
        return connecter;  
    }  
}
