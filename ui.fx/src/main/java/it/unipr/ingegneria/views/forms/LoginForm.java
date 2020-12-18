package it.unipr.ingegneria.ui.views.forms;

import it.unipr.ingegneria.ui.controllers.LoginController;

import it.unipr.ingegneria.ui.models.form.LoginInput;
import it.unipr.ingegneria.ui.views.component.panes.FormPane;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import it.unipr.ingegneria.ui.views.response.Error;
/**
 * The {@code LoginForm} is the form registration to Login
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class LoginForm implements  IForm<GridPane, LoginController> {
   // private  LoginInput.Field fields= new LoginInput.Field();
    private GridPane loginGrid;
    private  final Button LOGIN_BTN=new Button("Log in");

    public LoginForm(){
        this.loginGrid=new FormPane().getPane();
    }

    public String getTitle()
    {
        return LoginInput.Field.TITLE;
    }

    @Override
    public GridPane getGrid(LoginController controller)
    {

        GridPane.setConstraints(LoginInput.Field.EMAIL_LABEL,  0, 0);
        GridPane.setConstraints(LoginInput.Field.EMAIL_INPUT, 1, 0);
        GridPane.setConstraints(LoginInput.Field.PSW_LABEL, 0, 1);
        GridPane.setConstraints(LoginInput.Field.PSW_INPUT, 1, 1);

        this.LOGIN_BTN.setOnAction(e->{
            controller.register(LoginInput.Field.EMAIL_INPUT.getText(), LoginInput.Field.PSW_INPUT.getText());
                }
        );
        GridPane.setConstraints(this.LOGIN_BTN, 1, 2);

        this.loginGrid.getChildren().addAll(LoginInput.Field.EMAIL_LABEL, LoginInput.Field.EMAIL_INPUT, LoginInput.Field.PSW_LABEL, LoginInput.Field.PSW_INPUT, this.LOGIN_BTN);
        return this.loginGrid;
    }
}
