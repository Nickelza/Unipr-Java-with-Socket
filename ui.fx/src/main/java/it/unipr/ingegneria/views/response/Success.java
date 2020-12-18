package it.unipr.ingegneria.ui.views.response;

import it.unipr.ingegneria.ui.models.utils.Response;
import it.unipr.ingegneria.ui.views.component.panes.FormPane;
import javafx.scene.layout.GridPane;

/**
 * The {@code Success} is a javaFX class to output the positive response after send a form
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Success {
    private  Response.Field fields= new Response.Field();
    private GridPane successGrid;

    public Success(String title, String message){
        this.successGrid=new FormPane().getPane();
        this.fields.setTitle(title);
        this.fields.setMessage(message);
    }

    public String getTitle()
    {
        return fields.getTitle();
    }

    public Response.Field getFields() {
        return fields;
    }

    public GridPane getGrid() {
        return successGrid;
    }

    public GridPane getfilledGrid() {
        this.successGrid=new FormPane().getPane();
        GridPane.setConstraints(this.fields.getMessage(), 1, 2);
        this.successGrid.getChildren().addAll(this.fields.getMessage());
        return successGrid;
    }
}
