package it.unipr.ingegneria.ui.views.views;

import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.ui.models.views.UserData;
import it.unipr.ingegneria.ui.views.component.panes.ListPane;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

import java.util.List;

/**
 * The {@code ListCustomer} is a javaFX class to output the clients present in the system
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see ui.views.views.IList
 */
public class ListCustomer implements IList<VBox> {
    private VBox clientPane;
    private final TableView TABLE = new TableView();
    private ObservableList<User> data;
    private final String TITLE = "List Client";

    public ListCustomer(List<User> clients) {
        this.clientPane = new ListPane().getPane();
        this.data = FXCollections.observableArrayList(clients);
    }

    public String getTitle() {
        return this.TITLE;
    }

    @Override
    public VBox getTable() {
        UserData.Coloumns.NAME.setCellValueFactory(
                new PropertyValueFactory<User, String>("name"));
        UserData.Coloumns.SURNAME.setCellValueFactory(
                new PropertyValueFactory<User, String>("surname"));
        UserData.Coloumns.EMAIL.setCellValueFactory(
                new PropertyValueFactory<User, String>("email"));
        this.TABLE.setItems(this.data);
        this.TABLE.getColumns().addAll(UserData.Coloumns.NAME, UserData.Coloumns.SURNAME, UserData.Coloumns.EMAIL);
        clientPane.getChildren().addAll(this.TABLE);
        return clientPane;
    }
}
