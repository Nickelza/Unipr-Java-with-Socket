package it.unipr.ingegneria.ui.views.views;

import it.unipr.ingegneria.db.DTO.OrderDTO;
import it.unipr.ingegneria.ui.models.views.OrderData;
import it.unipr.ingegneria.ui.views.component.panes.ListPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * The {@code ListOrder} is a javafx class to output the order present in the system
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see ui.views.views.IList
 */
public class ListOrder implements IList<VBox> {

    private VBox orderPane;
    private final TableView TABLE = new TableView();
    private final String TITLE = "List Order";
    private ObservableList<OrderDTO> data;

    public ListOrder(List<OrderDTO> orders) {
        this.orderPane = new ListPane().getPane();
        this.data = FXCollections.observableArrayList(orders);
    }

    public String getTitle() {
        return this.TITLE;
    }

    private Integer userId;
    private Integer orderId;
    private String wineName;
    private String orderDate;
    private Boolean orderDelevered;
    private Integer orderQty;

    @Override
    public VBox getTable() {
        OrderData.Coloumns.USER_ID.setCellValueFactory(
                new PropertyValueFactory<OrderDTO, String>("userId"));
        OrderData.Coloumns.ORDER_ID.setCellValueFactory(
                new PropertyValueFactory<OrderDTO, String>("orderId"));
        OrderData.Coloumns.ORDER_DATA.setCellValueFactory(
                new PropertyValueFactory<OrderDTO, String>("orderDate"));
        OrderData.Coloumns.WINE_NAME.setCellValueFactory(
                new PropertyValueFactory<OrderDTO, String>("wineName"));
        OrderData.Coloumns.QTY.setCellValueFactory(
                new PropertyValueFactory<OrderDTO, String>("orderQty"));
        OrderData.Coloumns.ORDER_DELIVERED.setCellValueFactory(
                new PropertyValueFactory<OrderDTO, String>("orderDelevered"));
        this.TABLE.setItems(this.data);
        this.TABLE.getColumns().addAll(OrderData.Coloumns.USER_ID, OrderData.Coloumns.ORDER_ID, OrderData.Coloumns.ORDER_DATA, OrderData.Coloumns.WINE_NAME, OrderData.Coloumns.QTY, OrderData.Coloumns.ORDER_DELIVERED);
        this.orderPane.getChildren().addAll(this.TABLE);
        return this.orderPane;
    }
}
