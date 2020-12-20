package it.unipr.ingegneria.views.views;

import it.unipr.ingegneria.controllers.VineyardController;
import it.unipr.ingegneria.controllers.users.ClientController;
import it.unipr.ingegneria.controllers.users.UserController;
import it.unipr.ingegneria.models.form.VineyardInput;
import it.unipr.ingegneria.views.component.panes.FormPane;
import it.unipr.ingegneria.views.forms.IForm;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;


/**
 * The {@code VineyardForm} is the form registration to vineyard
*
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class NotifyView {
    private GridPane notifyGrid;
    private  final Button CONFIRM_BTN =new Button("OK");
    private final String TITLE="Wine available notify";
    private Text message;

    public NotifyView(){
        this.notifyGrid =new FormPane().getPane();
    }
    public String getTitle() {
        return this.TITLE;
    }

    public void setMessage(String message) {
        this.message = new Text(message);
    }

    public GridPane getView(UserController controller)
    {
        GridPane.setConstraints(this.message,  0, 0);
        this.CONFIRM_BTN.setOnAction(e->{
            controller.getStage().close();
                }
        );
        GridPane.setConstraints(this.CONFIRM_BTN, 1, 1);

        this.notifyGrid.getChildren().addAll(this.message,  this.CONFIRM_BTN);
        return this.notifyGrid;
    }
}
