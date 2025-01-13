
package javaapplication8;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import com.mysql.cj.jdbc.*;
import java.sql.*;


public class Conn {

    Connection c;
    Statement st;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankmanagementsystem","root","root");
            st = c.createStatement();
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }
}
