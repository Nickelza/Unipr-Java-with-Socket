package it.unipr.ingegneria.views.component;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * The {@code ListCustomer} is a javaFX class to output the clients present in the system
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 *
 */
public class LoadingView {
    private VBox loaderPane;
    private final String TITLE = "Loading";
    private final Float[] VALUES = new Float[] {-1.0f};
    private final Label LABEL = new Label("Loading");
    //final ProgressBar[] pbs = new ProgressBar[values.length];
    final ProgressIndicator[] pins = new ProgressIndicator[VALUES.length];
    final HBox hbs [] = new HBox [VALUES.length];

    public LoadingView() {
        this.loaderPane = new VBox();
    }

    public String getTitle() {
        return this.TITLE;
    }

    public synchronized VBox getView() {
        for (int i = 0; i < this.VALUES.length; i++) {

            final ProgressIndicator pin = pins[i] = new ProgressIndicator();
            pin.setProgress(this.VALUES[i]);
            final HBox hb = hbs[i] = new HBox();
            hb.setSpacing(5);
            hb.setAlignment(Pos.CENTER);
            hb.getChildren().addAll(this.LABEL, pin );

        }
        this.loaderPane.setSpacing(5);
        this.loaderPane.getChildren().addAll(hbs);
        return loaderPane;
    }
}
