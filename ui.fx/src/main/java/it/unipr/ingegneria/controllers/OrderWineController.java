package it.unipr.ingegneria.ui.controllers;

import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.db.DTO.OrderDTO;
import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.create.CreateOrderCriteria;
import it.unipr.ingegneria.request.create.CreateSendOrderCriteria;
import it.unipr.ingegneria.request.search.OrderSearchCriteria;
import it.unipr.ingegneria.ui.models.utils.Size;
import it.unipr.ingegneria.ui.views.component.panes.MainPane;
import it.unipr.ingegneria.ui.views.component.stage.BuilderStage;
import it.unipr.ingegneria.ui.views.forms.OrderWineForm;
import it.unipr.ingegneria.ui.views.menu.Menu;
import it.unipr.ingegneria.ui.views.response.Success;
import it.unipr.ingegneria.ui.views.views.ListOrder;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import it.unipr.ingegneria.ui.views.response.Error;

;import java.util.List;


public class OrderWineController {
    private OrderWineForm form;
    private BuilderStage orderStage;
    private ClientSocket clientSocket;
    private User user;
    private  Size.Field dim= new Size.Field();
    private Menu menu;


    public OrderWineController(ClientSocket clientSocket, Menu clientMenu,  User userAuthenticate){
        this.form =new OrderWineForm();
        this.clientSocket=clientSocket;
        this.user=userAuthenticate;
        this.menu = clientMenu;

    }
    public void register(String name, int qty){
        try {
            String msgSuccess = "Order wine is made whit success";
            String msgError = "Error to Order Wine";

            CreateOrderCriteria createOrderChiantiCriteria = new CreateOrderCriteria()
                    .setInQuantity(qty)
                    .setName(name)
                    .setUser(this.user);
            Order order=clientSocket.createOrder(createOrderChiantiCriteria);
            this.orderStage.getStage().close();
            if (order != null) {
                Success success = new Success(form.getTitle(), msgSuccess);
                BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), success.getfilledGrid());
                this.orderStage = new BuilderStage(success.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
                this.orderStage.getStage().show();
            } else {
                Error error=new Error(form.getTitle(), msgSuccess );
                BorderPane mainView= new MainPane().setMainView(this.menu.getMenu(),error.getGrid());
                this.orderStage = new BuilderStage(error.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
                this.orderStage.getStage().show();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void getForm(){
        BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), form.getGrid(this));
        this.orderStage = new BuilderStage(form.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
        this.orderStage.getStage().show();

    }
    public void send(){
        try {
            String msgSuccess = "All the order sent";
            String msgError = "Error to sent order";

            CreateSendOrderCriteria createSendOrderCriteria = new CreateSendOrderCriteria();
            String order=clientSocket.sendOrders(createSendOrderCriteria);

            if (order != null) {
                System.out.println(order);
                Success success = new Success(form.getTitle(), msgSuccess);
                BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), success.getfilledGrid());
                this.orderStage = new BuilderStage(success.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
                this.orderStage.getStage().show();
            } else {
                Error error=new Error(form.getTitle(), msgSuccess );
                BorderPane mainView= new MainPane().setMainView(this.menu.getMenu(),error.getGrid());
                this.orderStage = new BuilderStage(error.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
                this.orderStage.getStage().show();
            }
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public void getAll() {
        OrderSearchCriteria orderSearchCriteria = new OrderSearchCriteria().setSelectAll(true);
        List<OrderDTO> orderDTOS = clientSocket.searchOrders(orderSearchCriteria);
        System.out.println(orderDTOS);
        ListOrder allOrders = new ListOrder(orderDTOS);
        BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), allOrders.getTable());
        this.orderStage = new BuilderStage(allOrders.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
        this.orderStage.getStage().show();
    }
    public Stage getStage() {
        return orderStage.getStage();
    }
    public Menu getMenu(){
        return menu;
    }
}
