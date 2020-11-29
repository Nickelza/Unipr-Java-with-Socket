package it.unipr.ingegneria;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.ModelRequest;
import it.unipr.ingegneria.request.UserLoginRequest;
import it.unipr.ingegneria.request.create.CreateRequestManager;
import it.unipr.ingegneria.request.search.SearchRequestManager;
import it.unipr.ingegneria.response.ModelListResponse;
import it.unipr.ingegneria.response.ModelResponse;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class ServerMaster {
    private static final int SPORT = 4445;

    private WineShop shop;
    private ObjectInputStream is;
    private ObjectOutputStream os;

    public ServerMaster() {
        try {
            // Clear all tables by previous running
            this.clearAllTables();

            // Define the default WineShop that will be used in the lifecycle application
            this.shop = new WineShop("Enoteca Galvani");
            this.shop.persist();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void reply() throws IOException {
        ServerSocket server = new ServerSocket(SPORT);
        Socket client = server.accept();
        is = new ObjectInputStream(client.getInputStream());
        os = new ObjectOutputStream(client.getOutputStream());
        boolean exit = false;
        while (!exit) {
            try {

                Object o = is.readObject();

                if ((o != null) && (o instanceof ModelRequest)) {
                    switch (((ModelRequest<?>) o).getType()) {

                        case "UserLoginRequest":
                            UserLoginRequest r = (UserLoginRequest) o;
                            User user = shop.findByMailAndPassword(r.getEmail(), r.getPassword());
                            if (user != null)
                                user.setWineshop(shop);
                            ModelResponse response = new ModelResponse<User>().withModel(user);
                            os.writeObject(response);
                            os.flush();
                            break;
                        case "CreateRequest":
                            ModelResponse responseCreate = CreateRequestManager.fillWithResponse(shop, o);
                            os.writeObject(responseCreate);
                            os.flush();
                            break;

                        case "SearchRequest":
                            ModelListResponse responseSearch = SearchRequestManager.fillWithResponse(shop, o);
                            os.writeObject(responseSearch);
                            os.flush();
                            break;
                        case "UserLogoutRequest":
                            exit = true;
                            break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                // Since is a two-way communication between the client and server, the server listen for new request
                // but there are times when client does not send nothing so the InputStream is empty and the readObject launch (rightly) the EOF exception
            }
        }
        server.close();

    }

    private static void clearAllTables() throws SQLException {

        Connection conn = DBContext.getConnection();
        Statement statement = conn.createStatement();
        statement.addBatch("DELETE FROM ORDER_ITEM");
        statement.addBatch("DELETE FROM REL_ORDER_USER");
        statement.addBatch("DELETE FROM REL_ORDER_WINE");
        statement.addBatch("DELETE FROM REL_USER_WINESHOP");
        statement.addBatch("DELETE FROM REL_USER_WINESHOP");
        statement.addBatch("DELETE FROM REL_WINESHOP_WAREHOUSE");
        statement.addBatch("DELETE FROM REL_WINE_VINEYARD");
        statement.addBatch("DELETE FROM REL_WINE_WAREHOUSE");
        statement.addBatch("DELETE FROM USER");
        statement.addBatch("DELETE FROM VINEYARD");
        statement.addBatch("DELETE FROM WINE");
        statement.addBatch("DELETE FROM WAREHOUSE");
        statement.addBatch("DELETE FROM WINESHOP");
        int[] rs = statement.executeBatch();
    }

    public static void main(final String[] args) throws IOException, SQLException {

        new ServerMaster().reply();
    }

}
