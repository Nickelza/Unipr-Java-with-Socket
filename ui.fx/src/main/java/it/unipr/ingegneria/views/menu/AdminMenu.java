package it.unipr.ingegneria.views.menu;

import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.controllers.OrderWineController;
import it.unipr.ingegneria.controllers.VineyardController;
import it.unipr.ingegneria.controllers.WineController;
import it.unipr.ingegneria.controllers.users.AdminController;
import it.unipr.ingegneria.controllers.users.CustomerController;
import it.unipr.ingegneria.controllers.users.EmployeeController;
import it.unipr.ingegneria.controllers.users.UserController;
import it.unipr.ingegneria.models.menu.AdminItems;
import it.unipr.ingegneria.models.menu.SetupMenu;
import it.unipr.ingegneria.models.utils.TypeSearch;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.apache.log4j.Logger;


/**
 * The {@code AdminMenu} is the menu of the Admin user
 * @see Menu
 * @see IMenu
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class AdminMenu extends Menu implements IMenu<AdminItems> {
    private UserController myController;
    private User userAuthenticate;
    private static final Logger LOGGER = Logger.getLogger(AdminMenu.class);


    public AdminMenu(ClientSocket clientSocket, AdminController adminController, User user){
        super(clientSocket);
        this.myController=adminController;
        this.userAuthenticate=user;
    }

    @Override
    public VBox getMenu(){
        VBox vbox=new VBox();
        for (AdminItems i: AdminItems.values()) {
            vbox.getChildren().add(item(i));
        }
        return vbox;
    }
    @Override
    public HBox item(AdminItems adminItem){
        Button btn=new Button(adminItem.toString());
        btn.setOnAction(e->{
            LOGGER.info("I'm the "+btn.getText());
            this.getController(adminItem);
        });
        btn.setPrefSize(SetupMenu.Field.WIDTH_ITEMS, SetupMenu.Field.HEIGHT_ITEMS);
        HBox hbox= new HBox(btn);
        return hbox;

    }

    @Override
    public void getController(AdminItems items)
    {
        switch(items) {
            case PROFILE:
                super.closeStage(this.myController);
                super.goToProfile(new AdminController(super.getClientSocket(), this), userAuthenticate);
                break;
            case INSERT_CLIENT:
                insertUser(new CustomerController(super.getClientSocket(), this));
                break;
            case INSERT_EMPLOYEE:
                insertUser(new EmployeeController(super.getClientSocket(), this));
                break;
            case VIEW_CLIENT:
                viewListUser(new CustomerController(super.getClientSocket(), this));
                break;
            case VIEW_EMPLOYEE:
                viewListUser(new EmployeeController(super.getClientSocket(), this));
                break;
            case INSERT_VINEYARD:
                manageVineyard(new VineyardController(super.getClientSocket(), this), items.toString());
                break;
            case VIEW_VINEYARD:
                manageVineyard(new VineyardController(super.getClientSocket(), this), items.toString());
                break;
            case VIEW_ORDER:
                super.closeStage(this.myController);
                OrderWineController orders =new OrderWineController(super.getClientSocket(), this, userAuthenticate);
                orders.getAll();
                super.setMenuStage(orders.getStage());
                break;
            case VIEW_WINE:
                super.closeStage(this.myController);
                WineController wine =new WineController(super.getClientSocket(), this);
                wine.getAll(TypeSearch.ALL,"");
                super.setMenuStage(wine.getStage());
                break;
            case LOGOUT:
                super.logout();
                super.closeStage(this.myController);
                break;
            default:
                LOGGER.info("Menu item don't present");
                break;
        }
    }
    public void insertUser(UserController controller){
        super.closeStage(this.myController);
        controller.getForm();
        super.setMenuStage(controller.getStage());
    }
    public void viewListUser(UserController controller){
        super.closeStage(this.myController);
        controller.getAll();
        super.setMenuStage(controller.getStage());

    }
    public void manageVineyard(VineyardController controller, String type){
        super.closeStage(this.myController);
        if (type=="View vineyard")
        {
            controller.getAll();
        }
        else
        {
            controller.getForm();
        }
        super.setMenuStage(controller.getStage());
    }
}
