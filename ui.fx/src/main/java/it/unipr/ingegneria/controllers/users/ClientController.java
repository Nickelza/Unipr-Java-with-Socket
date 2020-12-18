package it.unipr.ingegneria.controllers.users;

import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.search.UserSearchCriteria;
import it.unipr.ingegneria.api.IController;
import it.unipr.ingegneria.views.component.panes.MainPane;
import it.unipr.ingegneria.views.component.stage.BuilderStage;
import it.unipr.ingegneria.views.forms.users.ClientForm;
import it.unipr.ingegneria.views.menu.CustomerMenu;
import it.unipr.ingegneria.views.menu.Menu;
import it.unipr.ingegneria.views.response.Error;
import it.unipr.ingegneria.views.response.Success;
import it.unipr.ingegneria.views.views.ListCustomer;
import it.unipr.ingegneria.views.views.UserProfile;
import it.unipr.ingegneria.utils.Type;
import javafx.scene.layout.BorderPane;

import java.util.List;

public class ClientController extends UserController implements IController<ClientController> {
    private ClientForm form;
    private UserProfile clientProfile;
    private BuilderStage adminStage;
    private ClientSocket clientSocket;
    private Menu menu;

    public ClientController(ClientSocket clientSocket) {

        this.clientSocket = clientSocket;

    }

    public ClientController(ClientSocket clientSocket, Menu adminMenu) {
        this.clientSocket = clientSocket;
        this.menu = adminMenu;

    }

    public void getProfile(User userSignIn) {
        if ((menu == null) || (menu.getClass() != CustomerMenu.class)) {
            this.menu = new CustomerMenu(clientSocket, this, userSignIn);
        }
        this.clientProfile = new UserProfile(userSignIn);
        BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), clientProfile.getGrid());
        super.setBorderStage(clientProfile.getTitle(), mainView);
        super.getStage().show();
    }

    public void register(String name, String surname, String email, String password) {
        String msgSuccess = "Client registration whit success";
        String msgError = "Error to registration client";


        Customer user = (Customer) clientSocket.createUser(super.createUser(name, surname, email, password, Type.CLIENT));
        System.out.println("ID:" + user.getId() + "email:" + user.getEmail());
        super.getStage().close();
        if (user != null) {
            Success success = new Success(form.getTitle(), msgSuccess);
            BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), success.getfilledGrid());
            super.setBorderStage(success.getTitle(), mainView);
            super.getStage().show();
        } else {
            Error error = new Error(form.getTitle(), msgSuccess);
            BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), error.getGrid());
            super.setBorderStage(error.getTitle(), mainView);
            super.getStage().show();
        }
    }

    public void getForm() {
        this.form = new ClientForm();
        BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), form.getGrid(this));
        super.setBorderStage(form.getTitle(), mainView);
        super.getStage().show();

    }

    public void getAll() {
        UserSearchCriteria clientSearch = new UserSearchCriteria().setSelectAll(true).setUserType(Type.CLIENT);
        List<User> clients = clientSocket.searchUsers(clientSearch);
        ListCustomer allClients = new ListCustomer(clients);
        BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), allClients.getTable());
        super.setBorderStage(allClients.getTitle(), mainView);
        super.getStage().show();
    }

    @Override
    public ClientController getController() {
        return this;
    }

    public Menu getMenu() {
        return menu;
    }
}
