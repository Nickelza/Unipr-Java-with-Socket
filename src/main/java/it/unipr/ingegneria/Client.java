package it.unipr.ingegneria;

import it.unipr.ingegneria.request.create.CreateUserRequest;
import it.unipr.ingegneria.request.UserLoginRequest;
import it.unipr.ingegneria.request.search.OrderSearchCriteria;
import it.unipr.ingegneria.request.SearchRequest;
import it.unipr.ingegneria.request.search.UserSearchCriteria;
import it.unipr.ingegneria.request.search.WineSearchCriteria;
import it.unipr.ingegneria.util.ModelRequestType;
import it.unipr.ingegneria.util.Type;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private static final int SPORT = 4449;
    private static final String SHOST = "localhost";

    public void send() {
        try {
            Socket client = new Socket(SHOST, SPORT);


            CreateUserRequest createUserRequest = new CreateUserRequest()
                    .asType(ModelRequestType.CREATE_USER)
                    .setName("Ruslan")
                    .setSurname("Vasyunin")
                    .setEmail("ruslan.vasyunin@gmail.com")
                    .setPassword("P@ssw0rd");


            UserLoginRequest loginRequest = new UserLoginRequest()
                    .asType(ModelRequestType.LOGIN)
                    .setEmail("ruslan.vasyunin@gmail.com")
                    .setPassword("P@ssw0rd");

            WineSearchCriteria searchWinesCriteria = new WineSearchCriteria().setSelectAll(true);
            SearchRequest<WineSearchCriteria> searchWine = new SearchRequest<>()
                    .asType(ModelRequestType.SEARCH)
                    .withModel(searchWinesCriteria);

            OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria().setSelectAll(true);
            SearchRequest<OrderSearchCriteria> searchOrders = new SearchRequest<>()
                    .asType(ModelRequestType.SEARCH)
                    .withModel(orderSearchCriteria);

            UserSearchCriteria userSearchCriteria = new UserSearchCriteria().setSelectAll(true).setUserType(Type.ADMIN);
            SearchRequest<UserSearchCriteria> searchUsers = new SearchRequest<>()
                    .asType(ModelRequestType.SEARCH)
                    .withModel(userSearchCriteria);

            ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
            ObjectInputStream is = new ObjectInputStream(client.getInputStream());

            //while (true) {

            os.writeObject(createUserRequest);
            os.flush();

            Thread.sleep(1000);

            os.writeObject(loginRequest);
            os.flush();

            Thread.sleep(1000);

            os.writeObject(searchWine);
            os.flush();

            Thread.sleep(1000);

            os.writeObject(searchOrders);
            os.flush();

            os.writeObject(searchUsers);
            os.flush();

            Object o = is.readObject();
            if ((o != null) && (o instanceof Object)) { }




            client.close();
        } catch (IOException | ClassNotFoundException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(final String[] args) {
        new Client().send();
    }
}
