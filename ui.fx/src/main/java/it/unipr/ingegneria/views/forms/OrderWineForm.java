package it.unipr.ingegneria.ui.views.forms;

import it.unipr.ingegneria.ui.controllers.OrderWineController;
import it.unipr.ingegneria.ui.models.form.OrderWineInput;
import it.unipr.ingegneria.ui.views.component.panes.FormPane;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;


/**
 * The {@code OrderWineForm} is the form registration to OrderWine
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class OrderWineForm implements IForm<GridPane, OrderWineController> {
    //private OrderWineInput.Field fields= new OrderWineInput.Field();
    private GridPane wineGrid;
    private final Button BUY_BTN = new Button("Buy wine");

    public OrderWineForm() {
        this.wineGrid = new FormPane().getPane();
    }

    public String getTitle() {
        return OrderWineInput.Field.TITLE;
    }

    @Override
    public GridPane getGrid(OrderWineController controller) {
        GridPane.setConstraints(OrderWineInput.Field.WINE_LABEL, 0, 0);
        GridPane.setConstraints(OrderWineInput.Field.WINE_INPUT, 1, 0);
        GridPane.setConstraints(OrderWineInput.Field.QTY_LABEL, 0, 1);
        GridPane.setConstraints(OrderWineInput.Field.QTY_INPUT, 1, 1);
        this.BUY_BTN.setOnAction(e -> {
                    controller.register(OrderWineInput.Field.WINE_INPUT.getText(), Integer.parseInt(OrderWineInput.Field.QTY_INPUT.getText()));
                    controller.getMenu().setMenuStage(controller.getStage());
                }
        );
        GridPane.setConstraints(this.BUY_BTN, 1, 4);
        this.wineGrid.getChildren().addAll(OrderWineInput.Field.WINE_LABEL, OrderWineInput.Field.WINE_INPUT, OrderWineInput.Field.QTY_LABEL, OrderWineInput.Field.QTY_INPUT, this.BUY_BTN);
        return this.wineGrid;
    }
}
