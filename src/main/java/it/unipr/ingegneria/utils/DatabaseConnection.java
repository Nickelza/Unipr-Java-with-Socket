package it.unipr.ingegneria.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * {@code DatabaseConnection} is a Singleton class to establish connection with the Database
 * This will remove the necessity to establish a connection in each class that access the Database
public class DatabaseConnection {

    private static Connection con = null;

    static
    {
        String url = "jdbc:mysql://localhost:3306/Winery?serverTimezone=UTC";
        String user = "root";
        String pass = "yK2niB4x5xV$EWY$gp6tcETigK8AxM%^YQv#OV1P@";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
        }
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection()
    {
        return con;
    }
}
