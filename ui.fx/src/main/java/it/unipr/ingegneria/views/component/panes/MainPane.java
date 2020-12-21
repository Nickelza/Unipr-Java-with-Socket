package it.unipr.ingegneria.views.component.panes;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
/**
 * The {@code MainPane} is the pane that combine the menu and the views/form
 * @see IPane
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class MainPane implements  IPane<BorderPane>{
    private BorderPane border;

    public MainPane() {
        this.border=new BorderPane();
    }
    @Override
    public BorderPane getPane() {
        return border;
    }

    /**
     * Set main pane with a VBox and GridPane
     * @param menu
     * @param view
     * @return
     */
    public BorderPane  setMainView(VBox menu, GridPane view) {
        border.setLeft(menu);
        border.setCenter(view);
        return border;
    }

    /**
     * Set mai pane with 2 VBox
     * @param menu
     * @param view
     * @return
     */
    public BorderPane  setMainView(VBox menu, VBox view) {
        border.setLeft(menu);
        border.setCenter(view);
        return border;
    }
}
