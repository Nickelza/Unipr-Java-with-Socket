package it.unipr.ingegneria.views.forms;

import it.unipr.ingegneria.controllers.VineyardController;
import it.unipr.ingegneria.models.form.VineyardInput;
import it.unipr.ingegneria.views.component.panes.FormPane;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * The {@code VineyardForm} is the form registration to vineyard
 * @see IForm
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class VineyardForm implements IForm<GridPane, VineyardController>{
    private GridPane vineyardGrid;
    private  final Button REGISTER_BTN =new Button("Register vineyard");

    public VineyardForm(){
        this.vineyardGrid =new FormPane().getPane();
    }
    public String getTitle() {
        return VineyardInput.Field.TITLE;
    }

    @Override
    public GridPane getGrid(VineyardController controller)
    {
        GridPane.setConstraints(VineyardInput.Field.NAME_LABEL,  0, 0);
        GridPane.setConstraints(VineyardInput.Field.NAME_INPUT, 1, 0);
        this.REGISTER_BTN.setOnAction(e->{
                    controller.register(VineyardInput.Field.NAME_INPUT.getText());
                    controller.getMenu().setMenuStage(controller.getStage());
                }
        );
        GridPane.setConstraints(this.REGISTER_BTN, 1, 1); //second coloumn, third row

        this.vineyardGrid.getChildren().addAll(VineyardInput.Field.NAME_LABEL, VineyardInput.Field.NAME_INPUT,  this.REGISTER_BTN);
        return this.vineyardGrid;
    }
}
