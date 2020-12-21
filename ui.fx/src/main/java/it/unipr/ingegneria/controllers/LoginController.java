package it.unipr.ingegneria.controllers;


import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.UserLoginRequest;
import it.unipr.ingegneria.controllers.users.AdminController;
import it.unipr.ingegneria.controllers.users.CustomerController;
import it.unipr.ingegneria.controllers.users.EmployeeController;
import it.unipr.ingegneria.models.utils.Size;
import it.unipr.ingegneria.views.component.stage.BuilderStage;
import it.unipr.ingegneria.views.forms.LoginForm;
import it.unipr.ingegneria.utils.ModelRequestType;
import it.unipr.ingegneria.views.response.Error;

/**
 * The {@code LoginController} is a class that manage the login fofm
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class LoginController {
    private LoginForm form;
    private BuilderStage loginStage;
    private ClientSocket clientSocket;
    private  Size.Field dim= new Size.Field();


    public LoginController(ClientSocket clientSocket){
        this.form =new LoginForm();
        this.clientSocket=clientSocket;
    }
    public void register(String email, String password){
        try {
            UserLoginRequest userLoginRequest = new UserLoginRequest().asType(ModelRequestType.LOGIN).setEmail(email).setPassword(password);
            User userAuthenticate = clientSocket.loginUser(userLoginRequest);
            this.loginStage.getStage().close();
            //this.clientSocket=new ClientSocket();
            if (userAuthenticate==null)
            {
                Error error = new Error(form.getTitle(), "User is not present");
                this.loginStage = new BuilderStage(error.getTitle(), error.getGrid(), dim.WIDTH, dim.HEIGHT);
                this.loginStage.getStage().show();
            }
            else{
            switch(userAuthenticate.getUserType()) {
                case "ADMIN":
                    new AdminController(clientSocket).getProfile(userAuthenticate);
                    break;
                case "CLIENT":
                    new CustomerController(clientSocket).getProfile(userAuthenticate);
                    break;
                case "EMPLOYEE":
                    new EmployeeController(clientSocket).getProfile(userAuthenticate);
                    break;
                default:
                    System.out.println("I'm the "+userAuthenticate.getUserType());
            }
        }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void getForm(){
        this.loginStage=new BuilderStage(form.getTitle(), form.getGrid(this), dim.WIDTH, dim.HEIGHT);
        this.loginStage.getStage().show();
    }
}
