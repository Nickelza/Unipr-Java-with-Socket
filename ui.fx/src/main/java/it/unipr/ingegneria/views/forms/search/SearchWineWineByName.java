package it.unipr.ingegneria.ui.views.forms.search;

import it.unipr.ingegneria.ui.controllers.WineController;
import it.unipr.ingegneria.ui.models.form.SearchWineInput;
import it.unipr.ingegneria.ui.models.utils.TypeSearch;
import it.unipr.ingegneria.ui.views.forms.IForm;
import javafx.scene.layout.GridPane;


/**
 * The {@code SearchWineByName} is the class to find wine by name
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see Search
 */
public class SearchWineWineByName extends Search implements IForm<GridPane, WineController> {

    private GridPane searchByNameGrid;

    public SearchWineWineByName() {
        super();
        this.searchByNameGrid = super.getGrid();
    }

    public String getTitle() {
        return SearchWineInput.FieldByName.TITLE;
    }

    @Override
    public GridPane getGrid(WineController controller) {
        GridPane.setConstraints(SearchWineInput.FieldByName.NAME_LABEL, 0, 0);
        GridPane.setConstraints(SearchWineInput.FieldByName.NAME_INPUT, 1, 0);
        super.getButton().setOnAction(e -> {
                    controller.getAll(TypeSearch.BY_NAME, SearchWineInput.FieldByName.NAME_INPUT.getText());
                }
        );
        GridPane.setConstraints(super.getButton(), 1, 2); //second coloumn, third row

        this.searchByNameGrid.getChildren().addAll(SearchWineInput.FieldByName.NAME_LABEL, SearchWineInput.FieldByName.NAME_INPUT, super.getButton());
        return this.searchByNameGrid;
    }
}
