package it.unipr.ingegneria.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection connection = null;
    private final static String ADDRESS = "jdbc:mysql://localhost";
    private final static String DATABASE = "Winery";
    private final static String USER     = "winery_admin";
    private final static String PASSWORD = "hCCMHuX4YIs6Yv5l";
    private final static String PORT     = "3306";
    private final static String DRIVER   = "com.mysql.cj.jdbc.Driver";
    private final static String TIMEZONE   = "UTC";

    /**
     * Method that loads the specified driver
     *
     * @return void
     **/

    private static void loadDriver() {
        try {
            Class.forName(DRIVER);
        }
        catch (Exception e) {
            errorHandler("Failed to load the driver " + DRIVER, e);
        }
    }

    /**
     * Method that loads the connection into the right property
     *
     * @return void
     **/

    private static void loadConnection() {
        try {
            connection = DriverManager.getConnection(getFormatedUrl(), USER, PASSWORD);
        }
        catch (SQLException e) {
            errorHandler("Failed to connect to the database " + getFormatedUrl(), e);
        }
    }

    /**
     * Method that shows the errors thrown by the singleton
     *
     * @param  {String}    Message
     * @option {Exception} e
     * @return  void
     **/

    private static void errorHandler(String message, Exception e) {
        System.out.println(message);
        if (e != null) System.out.println(e.getMessage());
    }

    /**
     * Method that returns the formated URL to connect to the database
     *
     * @return {String}
     **/

    private static String getFormatedUrl() {
        return ADDRESS + ":" + PORT + "/" + DATABASE + "?serverTimezone=" + TIMEZONE;
    }

    /**
     * Static method that returns the instance for the singleton
     *
     * @return {Connection} connection
     **/

    public static Connection getConnection() {
        if (connection == null) {
            loadDriver();
            loadConnection();
        }
        return connection;
    }

    /**
     * Static method that close the connection to the database
     *
     * @return void
     **/

    public static void closeConnection() {
        if (connection == null) {
            errorHandler("No connection found", null);
        }
        else {
            try {
                connection.close();
                connection = null;
            }
            catch (SQLException e) {
                errorHandler("Failed to close the connection", e);
            }
        }
    }
}