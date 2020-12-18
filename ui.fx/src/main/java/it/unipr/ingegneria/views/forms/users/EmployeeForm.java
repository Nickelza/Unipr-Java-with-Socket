package it.unipr.ingegneria.views.forms.users;

import it.unipr.ingegneria.controllers.users.EmployeeController;
import it.unipr.ingegneria.views.forms.IForm;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * The {@code EmployeeForm} is the class used to register employee
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 * @see UserForm
 * @see IForm
 */
public class EmployeeForm extends UserForm implements IForm<GridPane, EmployeeController> {
    private GridPane employeeGrid;
    private final Button REGISTER_BTN = new Button("Register Employee");

    public EmployeeForm() {
        super();
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    @Override
    public GridPane getGrid(EmployeeController controller) {
        super.setTitle("Employee registration");
        this.employeeGrid = super.getGrid();
        this.REGISTER_BTN.setOnAction(e -> { //
                    System.out.println(super.getFields().NAME_INPUT.getText() + " " + super.getFields().SURNAME_INPUT.getText());
                    controller.register(super.getFields().NAME_INPUT.getText(), super.getFields().SURNAME_INPUT.getText(), super.getFields().EMAIL_INPUT.getText(), super.getFields().PSW_INPUT.getText());
                    controller.getMenu().setMenuStage(controller.getStage());
                }
        );
        GridPane.setConstraints(this.REGISTER_BTN, 1, 4); //second coloumn, third row

        this.employeeGrid.getChildren().addAll(this.REGISTER_BTN);
        return this.employeeGrid;

    }


}
