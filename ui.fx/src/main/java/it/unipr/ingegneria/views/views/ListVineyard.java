package it.unipr.ingegneria.ui.views.views;

import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.ui.models.views.VineyardData;
import it.unipr.ingegneria.ui.views.component.panes.ListPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;


import java.util.List;
/**
 * The {@code ListVineyard} is a javafx class to output the vineyards present in the system
 * @see ui.views.views.IList
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class ListVineyard implements  IList<VBox>{

    private VBox vineyardPane;
    private final TableView TABLE = new TableView();
    private final String TITLE="List Client";
    private  ObservableList<Vineyard> data;

    public ListVineyard(List<Vineyard> vineyards){
        this.vineyardPane=new ListPane().getPane();
        this.data = FXCollections.observableArrayList(vineyards);
    }
    public String getTitle() {
        return this.TITLE;
    }

    @Override
    public VBox getTable() {
        VineyardData.Coloumns.NAME.setCellValueFactory(
                new PropertyValueFactory<Vineyard, String>("name"));
        TABLE.setItems(this.data);
        TABLE.getColumns().addAll(VineyardData.Coloumns.NAME);
        this.vineyardPane.getChildren().addAll(TABLE);
        return this.vineyardPane;

    }


}
