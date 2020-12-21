package it.unipr.ingegneria.controllers;

import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.request.create.CreateProvisioningCriteria;
import it.unipr.ingegneria.models.utils.Size;
import it.unipr.ingegneria.views.component.LoadingView;
import it.unipr.ingegneria.views.component.panes.MainPane;
import it.unipr.ingegneria.views.component.stage.BuilderStage;
import it.unipr.ingegneria.views.forms.ProvisioningWineForm;
import it.unipr.ingegneria.views.menu.Menu;
import it.unipr.ingegneria.views.response.Error;
import it.unipr.ingegneria.views.response.Success;
import javafx.application.Platform;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * The {@code ProvisioningWineController} is a class that manage the views about the provisioning of wine
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */public class ProvisioningWineController {
    private ProvisioningWineForm form;
    private BuilderStage provisioningStage;
    private ClientSocket clientSocket;
    private Size.Field dim = new Size.Field();
    private Menu menu;

    public ProvisioningWineController(ClientSocket clientSocket, Menu clientMenu) {
        VineyardController vineyardController = new VineyardController(clientSocket);
        this.form = new ProvisioningWineForm(vineyardController);
        this.clientSocket = clientSocket;
        this.menu = clientMenu;
    }

    public synchronized void register(String name, int year, String producer, String notes, int quantity, Vineyard vineyard) {
        try {
            String msgSuccess = "Provisioning wine is made whit success";
            String msgError = "Error to provisioning Wine";
            CreateProvisioningCriteria createProvisioningCriteriaWine =
                    new CreateProvisioningCriteria().setName(name)
                            .setProducer(producer)
                            .setTechNotes(notes)
                            .setYear(year)
                            .setInQuantity(quantity)
                            .setVineyard(vineyard);
            String results = this.clientSocket.createProvisioning(createProvisioningCriteriaWine);
            if (results != null) {
                Platform.runLater(
                        () -> {
                            this.provisioningStage.getStage().close();
                            Success success = new Success(form.getTitle(), msgSuccess);
                            BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), success.getfilledGrid());
                            this.provisioningStage = new BuilderStage(success.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
                            this.provisioningStage.getStage().show();
                            this.menu.setMenuStage(this.provisioningStage.getStage());
                        });
            } else {
                Platform.runLater(
                        () -> {
                            this.provisioningStage.getStage().close();
                            Error error = new Error(form.getTitle(), msgError);
                            BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), error.getGrid());
                            this.provisioningStage = new BuilderStage(error.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
                            this.provisioningStage.getStage().show();
                            this.menu.setMenuStage(this.provisioningStage.getStage());
                        });
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public  void waitingForRegistration(String name, int year, String producer, String notes, int quantity, Vineyard vineyard){
        try {
            Thread loader = new Thread(() -> {
                Platform.runLater(
                        () -> {
                            this.provisioningStage.getStage().close();
                            LoadingView loading = new LoadingView();
                            this.provisioningStage = new BuilderStage(loading.getTitle(), loading.getView(),  dim.WIDTH, dim.HEIGHT);
                            this.provisioningStage.getStage().show();
                        }
                );
            });
            Thread registration = new Thread(() -> {
                Platform.runLater(
                        () -> {
                            register(name, year, producer, notes, quantity, vineyard);
                        }
                );

            });
            loader.start();
            loader.join();
            registration.start();

        }
        catch (Exception e)
        {
            System.out.println(e);
        }
    }
    public void getForm() {
        BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), form.getGrid(this));
        this.provisioningStage = new BuilderStage(form.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
        this.provisioningStage.getStage().show();
    }

    public Stage getStage() {
        return provisioningStage.getStage();
    }

    public Menu getMenu() {
        return menu;
    }
}
