package it.unipr.ingegneria.models.form;

import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.controllers.VineyardController;
import javafx.collections.*;
import javafx.scene.control.*;

/**
 * The {@code ProvisioningWineInput} is a simple class to contains the information of provisioning form
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public  class ProvisioningWineInput {
        public final String TITLE = "Provisioning Wine";
        public final Label NAME_LABEL = new Label("Name");
        public final Label YEAR_LABEL = new Label("Year");
        public final Label PRODUCER_LABEL = new Label("Productor");
        public final Label NOTES_LABEL = new Label("Notes");
        public final Label VINEYARD_LABEL = new Label("Vineyard");
        public final Label QTY_LABEL = new Label("Quantity");
        public final TextField NAME_INPUT= new TextField();
        public   final TextField YEAR_INPUT= new TextField();
        public   final TextField PRODUCER_INPUT=new TextField();
        public   final TextField NOTES_INPUT=new TextField();
        private ListView<Vineyard> vineyardListView;
        public final  TextField QTY_INPUT= new TextField();

        public ProvisioningWineInput(VineyardController vineyard){
            ObservableList<Vineyard> oListVineyards = FXCollections.observableArrayList(vineyard.getList());
            this.setVineyardListView(oListVineyards);

        };

        public ProvisioningWineInput setVineyardListView(ObservableList<Vineyard> listVineyards) {
            this.vineyardListView = new ListView<>(listVineyards);
            vineyardListView.setCellFactory(param -> new ListCell<Vineyard>() {
                @Override
                protected void updateItem(Vineyard item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty || item == null || item.getName() == null) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }
                }
            });
            return this;
        }

        public ListView<Vineyard> getVineyardListView() {
            return vineyardListView;
        }
    }