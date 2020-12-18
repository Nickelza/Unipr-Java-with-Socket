package it.unipr.ingegneria.ui.models.views;

import it.unipr.ingegneria.entities.user.User;
import javafx.scene.control.TableColumn;
import javafx.scene.text.Text;

import javafx.scene.control.Label;

public class UserData {

    private  Text name;
    private  Text surname;
    private  Text email;

    public UserData(User user){
        this.name=new Text(user.getName());
        this.surname=new Text(user.getSurname());
        this.email=new Text(user.getEmail());
    }
        public Text getName() {
            return name;
        }
        public Text getSurname() {
            return surname;
        }
        public Text getEmail() {
            return email;
        }

    public static class Labels{
        public static final Label NAME=new Label("Name");
        public static final Label SURNAME=new Label("Surname");
        public static  final Label EMAIL=new Label("Email");

    }
    public static class Coloumns{
        public static final TableColumn NAME = new TableColumn("Name");
        public static final TableColumn SURNAME = new TableColumn("Surname");
        public static final TableColumn EMAIL = new TableColumn("Email");

    }




}
