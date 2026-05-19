package utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnectionConfigure {
    private static String username = "postgres";
    private static String password = "246446";
    private static String url = "jdbc:postgresql://localhost:5432/user_db";
    public static Connection getConnection() {
        try{
            return DriverManager.getConnection(
                    url,
                    username,
                    password
            );
        }catch (Exception e){
            System.out.println("Error during connection");
        }
        return null;
    }
}
