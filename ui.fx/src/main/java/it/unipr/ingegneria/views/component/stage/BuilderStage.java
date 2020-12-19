package it.unipr.ingegneria.views.component.stage;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * The {@code BuilderStage} is a javaFX class to set the stage
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class BuilderStage {
    private Stage stage;

    /**
     * Method to set a stage with a grid pane
     * @param title
     * @param grid
     * @param weight
     * @param height
     */
    public BuilderStage(String title, GridPane grid, int weight, int height){
        this.stage= new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(grid, weight, height));
    }
    /**
     * Method to set a stage with a border pane
     * @param title
     * @param border
     * @param weight
     * @param height
     */
    public BuilderStage(String title, BorderPane border, int weight, int height){
        this.stage= new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(border, weight, height));
    }
    public BuilderStage(String title, VBox view, int weight, int height){
        this.stage= new Stage();
        stage.setTitle(title);
        stage.setScene(new Scene(view, weight, height));
    }

    public Stage getStage() {
        return stage;
    }

}
