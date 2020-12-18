package it.unipr.ingegneria.views.response;

import it.unipr.ingegneria.models.utils.Response;
import it.unipr.ingegneria.views.component.panes.FormPane;
import javafx.scene.layout.GridPane;

/**
 * The {@code Error} is a javaFX class to output the negative response after send a form
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class Error {
    private  Response.Field fields= new Response.Field();
    private GridPane ErrorGrid;

    public Error(String title, String message){
        FormPane formGrid=new FormPane();
        this.ErrorGrid=formGrid.getPane();
        fields.setTitle(title);
        fields.setMessage(message);
    }
    public String getTitle()
    {
        return fields.getTitle();
    }

    public GridPane getGrid()
    {
       //email
        GridPane.setConstraints(fields.getMessage(),  1, 2);
        this.ErrorGrid.getChildren().addAll(fields.getMessage());
        return this.ErrorGrid;
    }
}
