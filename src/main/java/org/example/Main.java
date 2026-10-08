package org.example;
import java.sql.Connection;
import java.sql.DriverManager;


public class Main
{
    public static void main( String[] args )
    {
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";
        String pwd = "Dagi@12c";

        try {
            Connection con = DriverManager.getConnection(url, user, pwd);

            if (con != null) {
                System.out.println("Successfully connected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}