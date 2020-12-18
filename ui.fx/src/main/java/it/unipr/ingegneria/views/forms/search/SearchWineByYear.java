package it.unipr.ingegneria.ui.views.forms.search;

import it.unipr.ingegneria.ui.controllers.WineController;
import it.unipr.ingegneria.ui.models.form.SearchWineInput;
import it.unipr.ingegneria.ui.models.utils.TypeSearch;
import it.unipr.ingegneria.ui.views.forms.IForm;
import javafx.scene.layout.GridPane;


/**
 * The {@code SearchWineByYear} is the class to find wine by year
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see Search
 */
public class SearchWineByYear extends Search implements IForm<GridPane, WineController> {
    private GridPane searchByYearGrid;

    public SearchWineByYear() {
        super();
        this.searchByYearGrid = super.getGrid();
    }

    public String getTitle() {
        return SearchWineInput.FieldByYear.TITLE;
    }

    @Override
    public GridPane getGrid(WineController controller) {
        GridPane.setConstraints(SearchWineInput.FieldByYear.YEAR_LABEL, 0, 0);
        GridPane.setConstraints(SearchWineInput.FieldByYear.YEAR_INPUT, 1, 0);
        super.getButton().setOnAction(e -> {
                    controller.getAll(TypeSearch.BY_YEAR, SearchWineInput.FieldByYear.YEAR_INPUT.getText());

                }
        );
        GridPane.setConstraints(super.getButton(), 1, 2); //second coloumn, third row

        this.searchByYearGrid.getChildren().addAll(SearchWineInput.FieldByYear.YEAR_LABEL, SearchWineInput.FieldByYear.YEAR_INPUT, super.getButton());
        return this.searchByYearGrid;
    }
}
