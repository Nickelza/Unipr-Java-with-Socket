package it.unipr.ingegneria;

import it.unipr.ingegneria.DTO.OrderDTO;
import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.UserLoginRequest;
import it.unipr.ingegneria.request.UserLogoutRequest;
import it.unipr.ingegneria.request.create.*;
import it.unipr.ingegneria.request.search.*;
import it.unipr.ingegneria.response.ModelListResponse;
import it.unipr.ingegneria.response.ModelResponse;
import it.unipr.ingegneria.utils.ModelRequestType;
import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;


/**
 * The {@code ClientSocket} define the object client that can be used for obtaining the response by socket server   .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class ClientSocket {
    private static final int SPORT = 4445;
    private static final String SHOST = "localhost";
    private Socket client;
    private ObjectOutputStream os;
    private ObjectInputStream is;
    private transient Logger logger = Logger.getLogger(ClientSocket.class);

    public ClientSocket() {
        try {
            this.client = new Socket(SHOST, SPORT);
            this.os = new ObjectOutputStream(client.getOutputStream());
            this.is = null;
        } catch (IOException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
    }

    /**
     * Create a request to send to Server with passed parameters for create a user
     *
     * @param createUserCriteria Criteria object containing the params
     */
    public User createUser(CreateUserCriteria createUserCriteria) {
        User results = null;
        try {

            CreateRequest createUserRequest =
                    new CreateRequest<>().asType(ModelRequestType.CREATE).withModel(createUserCriteria);

            os.writeObject(createUserRequest);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            // Object o = null;
            Object o = is.readObject();

            if ((o != null) && (o instanceof ModelResponse)) {
                results = (User) ((ModelResponse<?>) o).getModel();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }

    /**
     * Try to login with passed parameter in UserLoginRequest object for verify if user is present in the database
     *
     * @param userLoginRequest object containing the params required for logins
     * @return User if present else a null value
     */
    public User loginUser(UserLoginRequest userLoginRequest) {
        User results = null;
        try {
            os.writeObject(userLoginRequest);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            Object o = is.readObject();

            if ((o != null) && (o instanceof ModelResponse)) {
                results = (User) ((ModelResponse<?>) o).getModel();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }

        return results;
    }


    /**
     * Logout with passed parameter
     *
     * @param userLogoutRequest object containing the params required for logut
     */
    public void logoutUser(UserLogoutRequest userLogoutRequest) {
        try {
            os.writeObject(userLogoutRequest);
            os.flush();


        } catch (IOException e) {
            logger.error(e);
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                logger.error(e);
            } catch (Exception e) {
                logger.error(e);
            }
        }

    }

    /**
     * Create a request to send to Server with passed parameters for start a provisioning of a specific Wine
     *
     * @param createProvisioningCriteria Criteria object containing the params
     * @return String with message of success or error
     */
    public String createProvisioning(CreateProvisioningCriteria createProvisioningCriteria) {
        String results = null;
        try {
            CreateRequest<CreateProvisioningCriteria> provisioningRequest =
                    new CreateRequest<>().asType(ModelRequestType.CREATE)
                            .withModel(createProvisioningCriteria);

            os.writeObject(provisioningRequest);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            Object o = is.readObject();

            if ((o != null) && (o instanceof ModelResponse)) {
                results = (String) ((ModelResponse<?>) o).getModel();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }

    /**
     * Create a request to send to Server with passed parameters for create params
     *
     * @param createProvisioningCriteria Criteria object containing the params
     * @return Vineyard object containing the id
     */
    public Vineyard createVineyard(CreateVineyardCriteria createProvisioningCriteria) {
        Vineyard results = null;
        try {
            CreateRequest<CreateVineyardCriteria> provisioningRequest =
                    new CreateRequest<>().asType(ModelRequestType.CREATE)
                            .withModel(createProvisioningCriteria);

            os.writeObject(provisioningRequest);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            Object o = is.readObject();

            if ((o != null) && (o instanceof ModelResponse)) {
                results = (Vineyard) ((ModelResponse<?>) o).getModel();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }


    /**
     * Create a request to send to Server with passed parameters for create and order params
     *
     * @param createOrderCriteria Criteria object containing the params
     * @return Order object containing the id
     */
    public Order createOrder(CreateOrderCriteria createOrderCriteria) {
        Order results = null;
        try {
            CreateRequest<CreateOrderCriteria> provisioningRequest =
                    new CreateRequest<>().asType(ModelRequestType.CREATE)
                            .withModel(createOrderCriteria);

            os.writeObject(provisioningRequest);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            Object o = is.readObject();

            if ((o != null) && (o instanceof ModelResponse)) {
                results = (Order) ((ModelResponse<?>) o).getModel();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }

    /**
     * Create a request to send to Server with passed parameters for search Wine by specified parameter in object
     *
     * @param searchWinesCriteria Criteria object containing the params
     * @return List of Wine Object
     */
    public List<Wine> searchWines(WineSearchCriteria searchWinesCriteria) {
        List<Wine> results = null;
        try {
            SearchRequest<WineSearchCriteria> searchWine = new SearchRequest<>()
                    .asType(ModelRequestType.SEARCH)
                    .withModel(searchWinesCriteria);

            os.writeObject(searchWine);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            Object o = is.readObject();

            if ((o != null) && (o instanceof ModelListResponse)) {
                results = (List<Wine>) ((ModelListResponse) o).getModels();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }

    /**
     * Create a request to send to Server with passed parameters for search Vineyard by specified parameter in object
     *
     * @param searchVineyardCriteria Criteria object containing the params
     * @return List of Wine Vineyard
     */
    public List<Vineyard> searchVineyards(SearchVineyardCriteria searchVineyardCriteria) {
        List<Vineyard> results = null;
        try {
            SearchRequest<WineSearchCriteria> searchWine = new SearchRequest<>()
                    .asType(ModelRequestType.SEARCH)
                    .withModel(searchVineyardCriteria);

            os.writeObject(searchWine);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            Object o = is.readObject();

            if ((o != null) && (o instanceof ModelListResponse)) {
                results = (List<Vineyard>) ((ModelListResponse) o).getModels();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }

    /**
     * Create a request to send to Server with passed parameters for search User by specified parameter in object
     *
     * @param userSearchCriteria Criteria object containing the params
     * @return List of User
     */
    public List<User> searchUsers(UserSearchCriteria userSearchCriteria) {
        List<User> results = null;
        try {
            SearchRequest<WineSearchCriteria> searchWine = new SearchRequest<>()
                    .asType(ModelRequestType.SEARCH)
                    .withModel(userSearchCriteria);

            os.writeObject(searchWine);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            Object o = is.readObject();

            if ((o != null) && (o instanceof ModelListResponse)) {
                results = (List<User>) ((ModelListResponse) o).getModels();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }

    /**
     * Create a request to send to Server with passed parameters for search Order by specified parameter in object
     *
     * @param orderSearchCriteria Criteria object containing the params
     * @return List of OrderDTO
     */
    public List<OrderDTO> searchOrders(OrderSearchCriteria orderSearchCriteria) {
        List<OrderDTO> results = null;
        try {
            SearchRequest<WineSearchCriteria> searchWine = new SearchRequest<>()
                    .asType(ModelRequestType.SEARCH)
                    .withModel(orderSearchCriteria);

            os.writeObject(searchWine);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            Object o = is.readObject();

            if ((o != null) && (o instanceof ModelListResponse)) {
                results = (List<OrderDTO>) ((ModelListResponse) o).getModels();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e);
        }
        return results;
    }

    /**
     * Create a request to send to Server with passed parameters for updated and send Orders
     *
     * @param createSendOrderCriteria Criteria object containing the params
     * @return List of OrderDTO
     */
    public String sendOrders(CreateSendOrderCriteria createSendOrderCriteria) {
        String results = null;
        try {
            SearchRequest<WineSearchCriteria> searchWine = new SearchRequest<>()
                    .asType(ModelRequestType.SEARCH)
                    .withModel(createSendOrderCriteria);

            os.writeObject(searchWine);
            os.flush();
            if (is == null) {
                is = new ObjectInputStream(new BufferedInputStream(
                        client.getInputStream()));
            }
            Object o = is.readObject();

            if ((o != null) && (o instanceof ModelResponse)) {
                results = (String) ((ModelResponse<?>) o).getModel();
            }

        } catch (IOException | ClassNotFoundException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
        return results;
    }


    /**
     * Close the socket connection
     */
    public void closeSocket() {
        try {
            this.client.close();
        } catch (IOException e) {
            logger.error(e);
        } catch (Exception e) {
            logger.error(e);
        }
    }


}
