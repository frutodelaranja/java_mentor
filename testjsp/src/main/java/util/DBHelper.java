package util;


import java.sql.Connection;
import java.sql.*;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static Connection connection;
    private  StringBuilder url = new StringBuilder();

    public DBHelper() {
        url.
                append("jdbc:mysql://").
                append("localhost:").
                append("3306/").
                append("java_pp?").
                append("user=root&").
                append("password=root");

        System.out.println("URL: " + url + "\n");

    }

    public  Connection getConnection() throws SQLException{
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return  connection = DriverManager.getConnection(url.toString());
    }


}
