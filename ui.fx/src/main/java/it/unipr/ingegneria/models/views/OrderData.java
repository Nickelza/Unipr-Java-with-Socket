package it.unipr.ingegneria.models.views;

import it.unipr.ingegneria.entities.user.User;
import javafx.scene.control.TableColumn;

public class OrderData {

    private OrderData(User user){ }

    public static class Coloumns{
        public static final TableColumn USER_ID = new TableColumn("User id");
        public static final TableColumn ORDER_ID = new TableColumn("Order id");
        public static final TableColumn ORDER_DATA = new TableColumn("Order data");
        public static final TableColumn WINE_NAME = new TableColumn("Wine");
        public static final TableColumn QTY = new TableColumn("Quantity");
        public static final TableColumn ORDER_DELIVERED = new TableColumn("Delivered");

    }




}
