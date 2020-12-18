package it.unipr.ingegneria.ui.views.forms.search;

import it.unipr.ingegneria.ui.controllers.WineController;
import it.unipr.ingegneria.ui.views.component.panes.FormPane;
import it.unipr.ingegneria.ui.views.forms.IForm;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * The {@code Search} is the class used to search wine
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Search implements IForm<GridPane, WineController> {
    private GridPane searchGrid;
    private final Button SEARCH_BUTTON = new Button("Search");

    public Search() {
        this.searchGrid = new FormPane().getPane();
    }

    public String getTitle() {
        return "Search Wine";
    }

    public Button getButton() {
        return SEARCH_BUTTON;
    }

    @Override
    public GridPane getGrid(WineController controller) {
        return this.searchGrid;
    }

    public GridPane getGrid() {
        return this.searchGrid;
    }
}
