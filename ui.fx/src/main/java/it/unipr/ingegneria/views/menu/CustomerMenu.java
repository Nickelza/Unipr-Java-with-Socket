package it.unipr.ingegneria.views.menu;

import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.controllers.OrderWineController;
import it.unipr.ingegneria.controllers.WineController;
import it.unipr.ingegneria.controllers.users.ClientController;
import it.unipr.ingegneria.controllers.users.UserController;
import it.unipr.ingegneria.controllers.NotifyWineController;
import it.unipr.ingegneria.models.menu.ClientItems;
import it.unipr.ingegneria.models.menu.SetupMenu;
import it.unipr.ingegneria.models.utils.TypeSearch;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.apache.log4j.Logger;


/**
 * The {@code CustomerMenu} is the menu of the Customer user
 * @see Menu
 * @see IMenu
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class CustomerMenu extends Menu implements IMenu<ClientItems> {
    private UserController myController;
    private User userAuthenticate;
    private static final Logger LOGGER = Logger.getLogger(CustomerMenu.class);
    private final NotifyWineController wineAvailable=new NotifyWineController();


    public CustomerMenu(ClientSocket clientSocket, ClientController clientController, User user){
        super(clientSocket);
        this.myController=clientController;
        this.userAuthenticate=user;
    }

    @Override
    public VBox getMenu(){
        VBox vbox=new VBox();
        for (ClientItems i: ClientItems.values()) {
            vbox.getChildren().add(item(i));
        }
        return vbox;
    }
    @Override
    public HBox item(ClientItems clientItem){
        Button btn=new Button(clientItem.toString());
        btn.setOnAction(e->{
            LOGGER.info("I'm the "+btn.getText());
            this.getController(clientItem);
        });
        btn.setPrefSize(SetupMenu.Field.WIDTH_ITEMS, SetupMenu.Field.HEIGHT_ITEMS);
        HBox hbox= new HBox(btn);
        return hbox;
    }

    @Override
    public void getController(ClientItems items)
    {
        switch(items) {
            case PROFILE:
                super.closeStage(this.myController);
                super.goToProfile(new ClientController(super.getClientSocket(), this), userAuthenticate);
                this.wineAvailable.check();
                break;
            case SEARCH_WINE_BY_NAME:
                searchWine(new WineController(super.getClientSocket(), this), TypeSearch.BY_NAME);
                break;
            case SEARCH_WINE_BY_YEAR:
                searchWine(new WineController(super.getClientSocket(), this), TypeSearch.BY_YEAR);
                break;
            case ORDER_WINE:
                super.closeStage(this.myController);
                OrderWineController order=new OrderWineController(super.getClientSocket(), this, userAuthenticate);
                order.getForm();
                this.wineAvailable.check();
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
    public void searchWine(WineController controller, TypeSearch type){
        super.closeStage(this.myController);
        controller.getSearch(type);
        this.wineAvailable.check();
        super.setMenuStage(controller.getStage());
    }

}
