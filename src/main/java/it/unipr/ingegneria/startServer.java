package it.unipr.ingegneria;

import java.io.IOException;
import java.sql.SQLException;

public class startServer {
    public static void main(final String[] args) throws IOException, SQLException {

        MainServer mainServer = new MainServer();
        mainServer.run();
    }
}
