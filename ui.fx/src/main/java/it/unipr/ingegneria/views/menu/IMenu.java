package it.unipr.ingegneria.views.menu;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public interface IMenu<T> {
    VBox getMenu();
    HBox item(T item);
    void getController(T item);

}
