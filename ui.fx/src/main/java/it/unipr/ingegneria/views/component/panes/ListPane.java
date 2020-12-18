package it.unipr.ingegneria.ui.views.component.panes;

import javafx.geometry.Insets;
import javafx.scene.layout.VBox;

/**
 * The {@code ListPane} is the pane for the list/menu views
 * @see ui.views.component.panes.IPane
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class ListPane  implements IPane<VBox>{
    private VBox vbox;
    public ListPane()
    {
        this.vbox=new VBox();
        this.vbox.setSpacing(5);
        this.vbox.setPadding(new Insets(10, 0, 0, 10));
    }

    public VBox getPane() {
        return vbox;
    }
}
