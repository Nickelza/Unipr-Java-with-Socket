package it.unipr.ingegneria.controllers.users;

import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.create.CreateUserCriteria;
import it.unipr.ingegneria.models.utils.Size;
import it.unipr.ingegneria.views.component.stage.BuilderStage;
import it.unipr.ingegneria.views.menu.Menu;
import it.unipr.ingegneria.utils.Type;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The {@code UserController} is father class that is used to manage the user views of this project
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class UserController {
    private BuilderStage stage;
    public Size.Field dim = new Size.Field();
    private Menu menu;


    public Stage getStage() {
        return stage.getStage();
    }

    public void setStage(BuilderStage stage) {
        this.stage = stage;
    }

    public void setGridStage(String title, GridPane pane) {
        this.stage = new BuilderStage(title, pane, dim.WIDTH, dim.HEIGHT);
    }

    public void setBorderStage(String title, BorderPane pane) {
        this.stage = new BuilderStage(title, pane, dim.WIDTH, dim.HEIGHT);
    }

    public CreateUserCriteria createUser(String name, String surname, String email, String password, Type type) {
        CreateUserCriteria createUserCriteria = new CreateUserCriteria()
                .setName(name)
                .setSurname(surname)
                .setEmail(email)
                .setPassword(password)
                .setUserType(type);
        return createUserCriteria;

    }
    public void closeStage(){
        if (this.stage.getStage()!=null)
        {
            this.stage.getStage().close();
        }
    }

    public void getForm(){}

    public void getAll(){}

    public void getProfile(User user){}

    public Menu getMenu(){
        return menu;
    }
}
