package it.unipr.ingegneria.models.views;

import it.unipr.ingegneria.entities.user.User;
import javafx.scene.control.TableColumn;

/**
 * The {@code OrderData} is a simple class used to rappresentation the order data
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class OrderData {

    private OrderData(){ }
    /**
     * The {@code Coloumns} is a static class that contains the coloumns name of the order table
     *
     * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
     */
    public static class Coloumns{
        public static final TableColumn USER_ID = new TableColumn("User id");
        public static final TableColumn ORDER_ID = new TableColumn("Order id");
        public static final TableColumn ORDER_DATA = new TableColumn("Order data");
        public static final TableColumn WINE_NAME = new TableColumn("Wine");
        public static final TableColumn QTY = new TableColumn("Quantity");
        public static final TableColumn ORDER_DELIVERED = new TableColumn("Delivered");
    }
}
