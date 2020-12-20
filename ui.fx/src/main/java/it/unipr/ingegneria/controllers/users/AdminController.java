package it.unipr.ingegneria.controllers.users;

import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.user.Admin;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.controllers.LoginController;
import it.unipr.ingegneria.views.component.panes.MainPane;
import it.unipr.ingegneria.views.forms.users.AdminForm;
import it.unipr.ingegneria.views.menu.AdminMenu;
import it.unipr.ingegneria.views.menu.Menu;
import it.unipr.ingegneria.views.response.Error;
import it.unipr.ingegneria.views.response.RegistrationAdminSuccess;
import it.unipr.ingegneria.views.views.UserProfile;
import it.unipr.ingegneria.utils.Type;
import javafx.scene.layout.BorderPane;

public class AdminController extends UserController {
    private AdminForm form;
    private UserProfile adminProfile;
    private ClientSocket clientSocket;
    private Menu menu;

    public AdminController(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public AdminController(ClientSocket clientSocket, Menu adminMenu) {
        this.clientSocket = clientSocket;
        this.menu = adminMenu;

    }

    public void getProfile(User userSignIn) {

        if ((menu == null) || (menu.getClass() != AdminMenu.class)) {
            this.menu = new AdminMenu(clientSocket, this, userSignIn);
        }
        this.adminProfile = new UserProfile(userSignIn);
        BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), adminProfile.getGrid());
        super.setBorderStage(adminProfile.getTitle(), mainView);
        super.getStage().show();
    }

    public void register(String name, String surname, String email, String password) {
        try {

            String msgSuccess = "Admin registration whit success";
            String msgError = "Error to registration Admin";

            Admin user = (Admin) this.clientSocket.createUser(super.createUser(name, surname, email, password, Type.ADMIN));
            System.out.println("ID:" + user.getId() + "email:" + user.getEmail());
            super.getStage().close();
            if (user != null) {
                RegistrationAdminSuccess success = new RegistrationAdminSuccess(form.getTitle(), msgSuccess, "Login", this);
                super.setGridStage(success.getTitle(), success.getGrid(new LoginController(clientSocket)));
                super.getStage().show();
            } else {
                Error error = new Error(form.getTitle(), msgError);
                BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), error.getGrid());
                super.setBorderStage(error.getTitle(), mainView);
                super.getStage().show();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void getForm() {

        this.form = new AdminForm();
        super.setGridStage(form.getTitle(), form.getGrid(this));
        super.getStage().show();
    }


}
