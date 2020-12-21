package it.unipr.ingegneria.views.component.panes;

import javafx.geometry.Insets;
import javafx.scene.layout.GridPane;
/**
 * The {@code FormPane} is the pane for the form views and profile views
 * @see IPane
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class FormPane implements IPane<GridPane>{
    private GridPane grid;
    public FormPane()
    {
        this.grid=new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8); //altezza del div
        grid.setHgap(10); //larghezza del div
    }

    @Override
    public GridPane getPane() {
        return grid;
    }
}
