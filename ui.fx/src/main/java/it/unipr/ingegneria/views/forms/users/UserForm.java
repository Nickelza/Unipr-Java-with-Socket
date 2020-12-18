package it.unipr.ingegneria.ui.views.forms.users;


import it.unipr.ingegneria.ui.models.form.UserInput;
import it.unipr.ingegneria.ui.views.component.panes.FormPane;
import javafx.scene.layout.GridPane;

/**
 * The {@code UserForm} is the father class to insert an user
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class UserForm {

    private GridPane userGrid;
    private UserInput.Field fields = new UserInput.Field();

    public UserForm() {
        this.userGrid = new FormPane().getPane();
    }

    public String getTitle() {
        return fields.getTitle();
    }

    public GridPane getGrid() {
        GridPane.setConstraints(fields.NAME_LABEL, 0, 0);
        GridPane.setConstraints(fields.NAME_INPUT, 1, 0);
        GridPane.setConstraints(fields.SURNAME_LABEL, 0, 1); //coloum, row
        GridPane.setConstraints(fields.SURNAME_INPUT, 1, 1);
        GridPane.setConstraints(fields.EMAIL_LABEL, 0, 2);
        GridPane.setConstraints(fields.EMAIL_INPUT, 1, 2);
        GridPane.setConstraints(fields.PSW_LABEL, 0, 3);
        GridPane.setConstraints(fields.PSW_INPUT, 1, 3);
        this.userGrid.getChildren().addAll(fields.NAME_LABEL, fields.NAME_INPUT, fields.SURNAME_LABEL, fields.SURNAME_INPUT, fields.EMAIL_LABEL, fields.EMAIL_INPUT, fields.PSW_LABEL, fields.PSW_INPUT);
        return this.userGrid;
    }

    public void setTitle(String title) {
        this.fields.setTitle(title);
    }

    public UserInput.Field getFields() {
        return fields;
    }


}
