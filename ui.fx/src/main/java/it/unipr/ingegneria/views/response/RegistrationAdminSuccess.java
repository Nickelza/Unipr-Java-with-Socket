package it.unipr.ingegneria.ui.views.response;

import it.unipr.ingegneria.ui.controllers.LoginController;
import it.unipr.ingegneria.ui.controllers.users.AdminController;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * The {@code RegistrationAdminSuccess} is a javaFX class to output the positive response after send a form of registration admin
 * this class extends {@code Success} class
 * @see Success
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class RegistrationAdminSuccess  extends  Success{
    private Button successBtn;
    private AdminController myController;

    public RegistrationAdminSuccess(String title, String message, String nameBtn, AdminController admin){
        super(title, message);
        this.successBtn=new Button(nameBtn);
        this.myController=admin;
    }
    public String getTitle()
    {
        return super.getTitle();
    }

    public GridPane getGrid(LoginController futureController)
    {
        GridPane.setConstraints(super.getFields().getMessage(),  1, 2);
        GridPane.setConstraints(this.successBtn,  1, 3);
        this.successBtn.setOnAction(e->{
            myController.getStage().close();
            futureController.getForm();
        });
        super.getGrid().getChildren().addAll(super.getFields().getMessage(), this.successBtn);
        return  super.getGrid();
    }
}
