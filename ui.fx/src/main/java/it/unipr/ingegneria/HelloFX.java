package it.unipr.ingegneria.ui;

import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.search.UserSearchCriteria;
import it.unipr.ingegneria.ui.controllers.LoginController;
import it.unipr.ingegneria.ui.controllers.users.AdminController;
import it.unipr.ingegneria.utils.Type;
import javafx.application.Application;
import javafx.stage.Stage;


import java.util.List;

public class HelloFX extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            ClientSocket clientSocket = new ClientSocket();
            UserSearchCriteria userSearchCriteriaByType = new UserSearchCriteria().setUserType(Type.ADMIN);
            List<User> listAdmin = clientSocket.searchUsers(userSearchCriteriaByType);
            if (listAdmin.isEmpty()) {
                AdminController admin = new AdminController(clientSocket);
                admin.getForm();
            } else {
                LoginController login = new LoginController(clientSocket);
                login.getForm();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}

