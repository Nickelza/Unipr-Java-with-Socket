package it.unipr.ingegneria.views.menu;

import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.controllers.OrderWineController;
import it.unipr.ingegneria.controllers.ProvisioningWineController;
import it.unipr.ingegneria.controllers.users.EmployeeController;
import it.unipr.ingegneria.controllers.users.UserController;
import it.unipr.ingegneria.controllers.NotifyWineController;
import it.unipr.ingegneria.models.menu.EmployeeItems;
import it.unipr.ingegneria.models.menu.SetupMenu;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;


/**
 * The {@code EmployyeMenu} is the menu of the Employee user
 * @see Menu
 * @see IMenu
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */

public class EmployeeMenu extends  Menu implements IMenu<EmployeeItems> {
    private UserController myController;
    private User userAuthenticate;
    private static final Logger LOGGER = Logger.getLogger(AdminMenu.class);
    private final NotifyWineController wineAvailable=new NotifyWineController();


    public EmployeeMenu(ClientSocket clientSocket, EmployeeController employeeController, User user ){
        super(clientSocket);
        this.myController=employeeController;
        this.userAuthenticate=user;
    }

    @Override
    public VBox getMenu(){
        VBox vbox=new VBox();
        //vbox.setPrefWidth(100);
        for (EmployeeItems i: EmployeeItems.values()) {
            vbox.getChildren().add(item(i));
        }
        return vbox;
    }

    @Override
    public HBox item(EmployeeItems employeeItem){
        Button btn=new Button(employeeItem.toString());
        btn.setOnAction(e->{
            LOGGER.info("I'm the "+btn.getText());
            this.getController(employeeItem);
        });
        btn.setPrefSize(SetupMenu.Field.WIDTH_ITEMS, SetupMenu.Field.HEIGHT_ITEMS);
        HBox hbox= new HBox(btn);
        return hbox;

    }
    @Override
    public void getController(EmployeeItems items)
    {
        switch(items) {
            case PROFILE:
                super.closeStage(this.myController);
                super.goToProfile(new EmployeeController(super.getClientSocket(), this), userAuthenticate);
                this.wineAvailable.requestOfWine();
                break;
            case PROVISIONING_WINE:
                super.closeStage(this.myController);
                ProvisioningWineController provisioningWine=new ProvisioningWineController(super.getClientSocket(), this, userAuthenticate);
                provisioningWine.getForm();
                this.wineAvailable.requestOfWine();
                super.setMenuStage(provisioningWine.getStage());
                break;
            case SEND_ORDERS:
                super.closeStage(this.myController);
                OrderWineController order=new OrderWineController(super.getClientSocket(), this, userAuthenticate);
                order.send();
                this.wineAvailable.requestOfWine();
                super.setMenuStage(order.getStage());
                break;
            case LOGOUT:
                super.logout(userAuthenticate);
                super.closeStage(this.myController);
                break;
            default:
                LOGGER.info("Menu item don't present");
                break;
        }
    }
}
