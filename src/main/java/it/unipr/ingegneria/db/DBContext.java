package it.unipr.ingegneria.db;

import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBContext {

    private static Connection conn = null;
    private static DBContext INSTANCE;
    private static final Logger LOGGER = Logger.getLogger(DBContext.class);

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:6603/unipr";
    static final String USER = "root";
    static final String PASS = "helloworld";


    private DBContext() {
        try {
            Class.forName(JDBC_DRIVER);
            LOGGER.info("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Open Connection to configured database
     *
     * @return the Connection to the configured database
     */
    public static synchronized Connection getConnection() {
        if (INSTANCE == null)
            INSTANCE = new DBContext();
        return conn;
    }


    /**
     * Close Connection to the configured database
     */
    public static synchronized void closeConnection() {
        try {
            LOGGER.info("Closing the connection to the database...");
            conn.close();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
