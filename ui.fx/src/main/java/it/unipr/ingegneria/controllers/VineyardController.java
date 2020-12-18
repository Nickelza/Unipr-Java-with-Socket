package it.unipr.ingegneria.controllers;


import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.request.create.CreateVineyardCriteria;
import it.unipr.ingegneria.request.search.SearchVineyardCriteria;
import it.unipr.ingegneria.models.utils.Size;
import it.unipr.ingegneria.views.component.panes.MainPane;
import it.unipr.ingegneria.views.component.stage.BuilderStage;
import it.unipr.ingegneria.views.forms.VineyardForm;
import it.unipr.ingegneria.views.menu.Menu;
import it.unipr.ingegneria.views.response.Error;
import it.unipr.ingegneria.views.response.Success;
import it.unipr.ingegneria.views.views.ListVineyard;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.util.List;

public class VineyardController {
    private VineyardForm form;
    private BuilderStage vineyardStage;
    private ClientSocket clientSocket;
    private Size.Field dim = new Size.Field();
    private Menu menu;

    public VineyardController(ClientSocket clientSocket) {
        this.form = new VineyardForm();
        this.clientSocket = clientSocket;


    }

    public VineyardController(ClientSocket clientSocket, Menu adminMenu) {
        this.form = new VineyardForm();
        this.clientSocket = clientSocket;
        this.menu = adminMenu;

    }

    public Stage getStage() {
        return vineyardStage.getStage();
    }

    public void register(String name) {
        try {
            String msgSuccess = "Vineyard registration whit success";
            String msgError = "Error to registration Vineyard";

            CreateVineyardCriteria createVineyardCriteria = new CreateVineyardCriteria().setName(name);


            Vineyard vineyard = this.clientSocket.createVineyard(createVineyardCriteria);
            System.out.println("ID:" + vineyard.getId() + "vineyard:" + vineyard.getName());
            this.vineyardStage.getStage().close();
            if (vineyard != null) {
                Success success = new Success(form.getTitle(), msgSuccess);
                BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), success.getfilledGrid());
                this.vineyardStage = new BuilderStage(success.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
                this.vineyardStage.getStage().show();
            } else {
                Error error = new Error(form.getTitle(), msgSuccess);
                BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), error.getGrid());
                this.vineyardStage = new BuilderStage(error.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
                this.vineyardStage.getStage().show();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void getForm() {
        BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), form.getGrid(this));
        this.vineyardStage = new BuilderStage(form.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
        this.vineyardStage.getStage().show();

    }

    public List<Vineyard> getList() {
        SearchVineyardCriteria searchVineyardCriteria = new SearchVineyardCriteria().setSelectAll(true);
        List<Vineyard> vineyards = clientSocket.searchVineyards(searchVineyardCriteria);
        return vineyards;
    }

    public void getAll() {
        SearchVineyardCriteria searchVineyardCriteria = new SearchVineyardCriteria().setSelectAll(true);
        List<Vineyard> vineyards = clientSocket.searchVineyards(searchVineyardCriteria);
        ListVineyard allVineyards = new ListVineyard(vineyards);
        BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), allVineyards.getTable());
        this.vineyardStage = new BuilderStage(allVineyards.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
        this.vineyardStage.getStage().show();
    }

    public Menu getMenu() {
        return menu;
    }
}
