package it.unipr.ingegneria.views.menu;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The {@code IMenu} is the interface that contain the principal operation of the menu
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public interface IMenu<T> {
    VBox getMenu();
    HBox item(T item);
    void getController(T item);

}
