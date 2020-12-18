package it.unipr.ingegneria.views.views;

import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.models.views.UserData;
import it.unipr.ingegneria.views.component.panes.FormPane;
import javafx.scene.layout.GridPane;


/**
 * The {@code UserProfile} is a javafx class to output the profile of the user that sign in
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class UserProfile {
    private GridPane userGrid;
    private UserData user;
    private final String TITLE = "Profile";

    public UserProfile(User user) {
        this.userGrid = new FormPane().getPane();
        this.user = new UserData(user);
    }

    public String getTitle() {
        return this.TITLE;
    }

    public GridPane getGrid() {
        GridPane.setConstraints(UserData.Labels.NAME, 0, 0);
        GridPane.setConstraints(user.getName(), 1, 0);
        GridPane.setConstraints(UserData.Labels.SURNAME, 0, 1);
        GridPane.setConstraints(user.getSurname(), 1, 1);
        GridPane.setConstraints(UserData.Labels.EMAIL, 0, 2);
        GridPane.setConstraints(user.getEmail(), 1, 2);
        this.userGrid.getChildren().addAll(UserData.Labels.NAME, user.getName(), UserData.Labels.SURNAME, user.getSurname(), UserData.Labels.EMAIL, user.getEmail());
        return this.userGrid;
    }


}
