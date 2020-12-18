package it.unipr.ingegneria.views.forms;

import it.unipr.ingegneria.controllers.ProvisioningWineController;
import it.unipr.ingegneria.controllers.VineyardController;
import it.unipr.ingegneria.models.form.ProvisioningWineInput;
import it.unipr.ingegneria.views.component.panes.FormPane;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * The {@code ProvisioningWineForm} is the form registration to ProvisioningWine
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class ProvisioningWineForm implements IForm<GridPane, ProvisioningWineController>{

    private ProvisioningWineInput fields;
    private GridPane wineGrid;
    private  final Button REGISTER_BTN=new Button("Provision wine");

    public ProvisioningWineForm(VineyardController vineyardController){
        FormPane formGrid=new FormPane();
        this.fields= new ProvisioningWineInput(vineyardController);
        this.wineGrid=formGrid.getPane();
    }
    public String getTitle()
    {
        return fields.TITLE;
    }

    @Override
    public GridPane getGrid(ProvisioningWineController controller)
    {
        GridPane.setConstraints(fields.NAME_LABEL,  0, 0);
        GridPane.setConstraints(fields.NAME_INPUT, 1, 0);
        //year
        GridPane.setConstraints(fields.YEAR_LABEL, 0, 1);
        GridPane.setConstraints(fields.YEAR_INPUT, 1, 1);
        //productor
        GridPane.setConstraints(fields.PRODUCER_LABEL, 0, 2);
        GridPane.setConstraints(fields.PRODUCER_INPUT, 1, 2);
        //notes
        GridPane.setConstraints(fields.NOTES_LABEL, 0, 3);
        GridPane.setConstraints(fields.NOTES_INPUT, 1, 3);

        GridPane.setConstraints(fields.VINEYARD_LABEL, 0, 4);
        GridPane.setConstraints(fields.getVineyardListView(), 1, 4);

        GridPane.setConstraints(fields.QTY_LABEL, 0, 5);
        GridPane.setConstraints(fields.QTY_INPUT, 1, 5);

        this.REGISTER_BTN.setOnAction(e->{
                    System.out.println(fields.NAME_INPUT.getText()+" "+fields.YEAR_INPUT.getText());
                    controller.register(fields.NAME_INPUT.getText(),Integer.parseInt(fields.YEAR_INPUT.getText()), fields.PRODUCER_INPUT.getText(), fields.NOTES_INPUT.getText(), Integer.parseInt(fields.QTY_INPUT.getText()), fields.getVineyardListView().getSelectionModel().getSelectedItem());
                    controller.getMenu().setMenuStage(controller.getStage());
                }
        );
        GridPane.setConstraints(this.REGISTER_BTN, 1, 6);
        this.wineGrid.getChildren().addAll(fields.NAME_LABEL, fields.NAME_INPUT, fields.YEAR_LABEL, fields.YEAR_INPUT, fields.PRODUCER_LABEL, fields.PRODUCER_INPUT, fields.NOTES_LABEL, fields.NOTES_INPUT, fields.QTY_LABEL, fields.QTY_INPUT, fields.VINEYARD_LABEL, fields.getVineyardListView(), this.REGISTER_BTN);
        return this.wineGrid;
    }
}
