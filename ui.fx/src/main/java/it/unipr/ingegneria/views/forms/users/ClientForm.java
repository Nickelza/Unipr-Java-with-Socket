package it.unipr.ingegneria.ui.views.forms.users;

import it.unipr.ingegneria.ui.controllers.users.ClientController;
import it.unipr.ingegneria.ui.views.forms.IForm;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * The {@code ClientForm} is the class used to register client
 * @see UserForm
 * @see IForm
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class ClientForm extends UserForm implements IForm<GridPane, ClientController> {
    private GridPane clientGrid;
    private  final Button REGISTER_BTN =new Button("Register Client");
    public ClientForm(){super();}

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public GridPane getGrid(ClientController controller) {
        super.setTitle("Client registration");
        this.clientGrid=super.getGrid();
        this.REGISTER_BTN.setOnAction(e->{ //
                    System.out.println(super.getFields().NAME_INPUT.getText()+" "+super.getFields().SURNAME_INPUT.getText());
                    controller.register(super.getFields().NAME_INPUT.getText(),super.getFields().SURNAME_INPUT.getText(), super.getFields().EMAIL_INPUT.getText(),super.getFields().PSW_INPUT.getText()  );
                    controller.getMenu().setMenuStage(controller.getStage());
                }
        );
        GridPane.setConstraints(this.REGISTER_BTN, 1, 4); //second coloumn, third row

        this.clientGrid.getChildren().addAll(this.REGISTER_BTN);
        return this.clientGrid;

    }


}
