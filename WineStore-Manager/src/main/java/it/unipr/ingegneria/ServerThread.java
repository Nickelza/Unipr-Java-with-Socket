package it.unipr.ingegneria;

import it.unipr.ingegneria.impl.WineShop;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.ModelRequest;
import it.unipr.ingegneria.request.UserLoginRequest;
import it.unipr.ingegneria.impl.CreateRequestManager;
import it.unipr.ingegneria.impl.SearchRequestManager;
import it.unipr.ingegneria.response.ModelListResponse;
import it.unipr.ingegneria.response.ModelResponse;

import java.io.*;
import java.net.Socket;

/**
 * The {@code ServerThread} class represent an instance server for a socket connection
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class ServerThread implements Runnable {

    private final MainServer server;
    private final Socket socket;
    private final WineShop shop;


    public ServerThread(final MainServer s, final Socket c, WineShop shop) {
        this.server = s;
        this.socket = c;
        this.shop = shop;
    }


    @Override
    public void run() {
        ObjectInputStream is = null;
        ObjectOutputStream os = null;

        try {
            is = new ObjectInputStream(new BufferedInputStream(
                    this.socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();

            return;
        }
        boolean exit = false;
        while (!exit) {
            try {

                Object o = is.readObject();
                if (os == null) {
                    os = new ObjectOutputStream(new BufferedOutputStream(
                            this.socket.getOutputStream()));
                }
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
                            if (this.server.getPool().getActiveCount() == 1)
                                this.server.close();


                            this.socket.close();
                            exit = true;
                            break;
                    }
                }
            } catch (IOException | ClassNotFoundException e) {
                // Since is a two-way communication between the client and server, the server listen for new request
                // but there are times when client does not send nothing (stay in idle) so the InputStream is empty and the readObject launch (rightly) the EOF exception

                // So the founded possibile solution are
                // - opening and closing the socket for every request
                // - "ignore"/skip the EOF exception.. since the socket will be closed when receive a UserLogoutRequest as the connection with socket act as a Session
            }
        }

    }
}
