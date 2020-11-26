package it.unipr.ingegneria;

import it.unipr.ingegneria.request.ModelRequest;
import it.unipr.ingegneria.request.SearchRequest;
import it.unipr.ingegneria.request.search.OrderSearchCriteria;
import it.unipr.ingegneria.request.search.UserSearchCriteria;
import it.unipr.ingegneria.request.search.WineSearchCriteria;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMaster {
    private static final int SPORT = 4449;

    public void reply() {
        try {
            ServerSocket server = new ServerSocket(SPORT);

            Socket client = server.accept();

            ObjectInputStream is = new ObjectInputStream(client.getInputStream());
            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());

            while (true) {
                Object o = is.readObject();

                if ((o != null) && (o instanceof ModelRequest)) {
                    switch (((ModelRequest<?>) o).getType()) {
                        case "UserLoginRequest":
                            System.out.format(" Server received: UserLoginRequest Client \n");
                            break;
                        case "CreateUserRequest":
                            // (CreateUserRequest) o;
                            System.out.format(" Server received: CreateUserRequest Client \n");
                            break;
                        case "CreateProvisioningRequest":
                            break;
                        case "CreateOrderRequest":
                            break;
                        case "SearchRequest":
                            if (((SearchRequest) o).getModel() instanceof WineSearchCriteria) {
                                System.out.format(" Server received SearchRequest with: WineSearchCriteria as Criteria \n");
                            }
                            if (((SearchRequest) o).getModel() instanceof OrderSearchCriteria) {
                                System.out.format(" Server received SearchRequest with: OrderSearchCriteria as Criteria \n");
                            }
                            if (((SearchRequest) o).getModel() instanceof UserSearchCriteria) {
                                System.out.format(" Server received SearchRequest with: UserSearchCriteria as Criteria \n");
                            }
                            break;
                        default:
                            return;
                    }


                    // os.writeObject(new Message("end"));
                    // os.flush();
                    //  break;

                }
            }

            // client.close();
            // server.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        new ServerMaster().reply();
    }

}
