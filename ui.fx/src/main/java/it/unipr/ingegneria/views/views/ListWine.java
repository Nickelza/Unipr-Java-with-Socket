package it.unipr.ingegneria.views.views;

import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.models.views.WineData;
import it.unipr.ingegneria.views.component.panes.ListPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import java.util.List;

/**
 * The {@code ListWine} is a javafx class to output the wines present in the system
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see  IList
 */
public class ListWine implements IList<VBox> {
    private VBox winePane;
    private final TableView TABLE = new TableView();
    private final String TITLE = "List Client";
    private ObservableList<Wine> data;

    public ListWine(List<Wine> wines) {
        this.winePane = new ListPane().getPane();
        this.data = FXCollections.observableArrayList(wines);
    }

    public String getTitle() {
        return this.TITLE;
    }

    @Override
    public VBox getTable() {
        WineData.Coloumns.WINE_ID.setCellValueFactory(
                new PropertyValueFactory<Wine, String>("id"));
        WineData.Coloumns.NAME.setCellValueFactory(
                new PropertyValueFactory<Wine, String>("name"));
        WineData.Coloumns.YEAR.setCellValueFactory(
                new PropertyValueFactory<Wine, String>("year"));
        WineData.Coloumns.PRODUCER.setCellValueFactory(
                new PropertyValueFactory<Wine, String>("producer"));
        WineData.Coloumns.TECH_NOTES.setCellValueFactory(
                new PropertyValueFactory<Wine, String>("techNotes"));
        WineData.Coloumns.VINEYARDS.setCellValueFactory(
                new PropertyValueFactory<Wine, List<Vineyard>>("vineyards"));
        WineData.Coloumns.VINEYARDS.setCellFactory(new Callback<TableColumn<Wine, List<Vineyard>>, TableCell<Wine, List<Vineyard>>>() {
            @Override
            public TableCell<Wine, List<Vineyard>> call(TableColumn<Wine, List<Vineyard>> param) {
                TableCell<Wine, List<Vineyard>> vineyardCell = new TableCell<Wine, List<Vineyard>>() {

                    @Override
                    protected void updateItem(List<Vineyard> item, boolean empty) {
                        if (item != null) {
                            for (Vineyard i : item
                            ) {
                                Label vineyardLabel = new Label(i.getName());
                                setGraphic(vineyardLabel);
                            }
                        }
                    }
                };
                return vineyardCell;
            }
        });
        this.TABLE.setItems(this.data);
        this.TABLE.getColumns().addAll(WineData.Coloumns.WINE_ID, WineData.Coloumns.NAME, WineData.Coloumns.YEAR, WineData.Coloumns.PRODUCER, WineData.Coloumns.TECH_NOTES, WineData.Coloumns.VINEYARDS);
        winePane.getChildren().addAll(this.TABLE);
        return winePane;
    }
}
