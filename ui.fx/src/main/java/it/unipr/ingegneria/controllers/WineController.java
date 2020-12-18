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

public class WineController {
    //private SearchWineByName searchByName;
    private BuilderStage wineStage;
    private ClientSocket clientSocket;
    private Size.Field dim = new Size.Field();
    private Menu menu;

    public WineController(ClientSocket clientSocket) {
        this.clientSocket = clientSocket;


    }

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
        catch (Exception e)
        {
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
            else if(type == TypeSearch.BY_YEAR)
                {
                System.out.println(Integer.parseInt(input));
                int value = Integer.parseInt(input.trim());
                WineSearchCriteria searchAllWinesCriteriaByYear = new WineSearchCriteria().setYear(value);
                 wines = clientSocket.searchWines(searchAllWinesCriteriaByYear);
                System.out.println(wines);
            }
            else
            {
                WineSearchCriteria searchAllWinesCriteria = new WineSearchCriteria().setSelectAll(true);
                wines = clientSocket.searchWines(searchAllWinesCriteria);
               // System.out.println(wines);
            }
            ListWine allwines=new ListWine(wines);
            BorderPane mainView = new MainPane().setMainView(this.menu.getMenu(), allwines.getTable());
            this.wineStage = new BuilderStage(allwines.getTitle(), mainView, dim.WIDTH, dim.HEIGHT);
            this.wineStage.getStage().show();
        }
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
    }
    public Menu getMenu(){
        return menu;
    }
}
