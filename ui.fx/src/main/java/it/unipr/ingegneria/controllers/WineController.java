package it.unipr.ingegneria.controllers;


import it.unipr.ingegneria.ClientSocket;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.request.search.WineSearchCriteria;
import it.unipr.ingegneria.models.utils.Size;
import it.unipr.ingegneria.models.utils.TypeSearch;
import it.unipr.ingegneria.views.component.panes.MainPane;
import it.unipr.ingegneria.views.component.stage.BuilderStage;
import it.unipr.ingegneria.views.forms.search.Search;
import it.unipr.ingegneria.views.forms.search.SearchWineByYear;
import it.unipr.ingegneria.views.forms.search.SearchWineWineByName;
import it.unipr.ingegneria.views.menu.Menu;
import it.unipr.ingegneria.views.views.ListWine;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


import java.util.List;

/**
 * The {@code WineController} is a class that manage the views about the Wine
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class WineController {
    private BuilderStage wineStage;
    private ClientSocket clientSocket;
    private Size.Field dim = new Size.Field();
    private Menu menu;


    public WineController(ClientSocket clientSocket, Menu clientMenu) {
        this.clientSocket = clientSocket;
        this.menu = clientMenu;
    }

    public Stage getStage() {
        return wineStage.getStage();
    }

    public void getSearch(TypeSearch type) {
        try {
            Search searchForm = null;
            if (type == TypeSearch.BY_NAME) {
                searchForm = new SearchWineWineByName();

            } else {
                searchForm = new SearchWineByYear();
            }
            BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), searchForm.getGrid(this));
            this.wineStage = new BuilderStage(searchForm.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
            this.wineStage.getStage().show();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }

    }
    public void getAll(TypeSearch type, String input) {
        try {
            List<Wine> wines=null;

            if (type == TypeSearch.BY_NAME) {
                WineSearchCriteria searchAllWinesCriteriaByName = new WineSearchCriteria().setName(input);
                wines = clientSocket.searchWines(searchAllWinesCriteriaByName);
            }
            else if(type == TypeSearch.BY_YEAR) {
                int value = Integer.parseInt(input.trim());
                WineSearchCriteria searchAllWinesCriteriaByYear = new WineSearchCriteria().setYear(value);
                wines = clientSocket.searchWines(searchAllWinesCriteriaByYear);
            }
            else {
                WineSearchCriteria searchAllWinesCriteria = new WineSearchCriteria().setSelectAll(true);
                wines = clientSocket.searchWines(searchAllWinesCriteria);

            }
            this.wineStage.getStage().close();
            ListWine allWines=new ListWine(wines);
            BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), allWines.getTable());
            this.wineStage = new BuilderStage(allWines.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
            this.wineStage.getStage().show();
        }
        catch (Exception e) {
            System.out.println(e.toString());
        }
    }
    public Menu getMenu(){
        return menu;
    }
}
