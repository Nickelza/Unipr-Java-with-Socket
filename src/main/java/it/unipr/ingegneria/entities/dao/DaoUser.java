package it.unipr.ingegneria.entities.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.api.IDaoGeneric;
import it.unipr.ingegneria.utils.DatabaseConnection;


/**
 * The {@code DaoUser} manages database access for the {@code User} entity
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class DaoUser implements IDaoGeneric<User> {

    static Connection con = DatabaseConnection.getConnection();

    /**
     * Method to add a {@code User}
     *
     * @param user
     * @return the added user id
     */
    @Override
    public int add(User user) throws SQLException{
        String query = "INSERT INTO User (name, surname, email, password, type) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, user.getName());
        ps.setString(2, user.getSurname());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPassword());
        ps.setString(5, "employee");
        int n = ps.executeUpdate();
        return n;
    }

    /**
     * Method to delete a {@code User}
     *
     * @param id Unique id of the searched user
     * @return success status
     */
    @Override
    public boolean delete(int id) {
        return false;
    }

    /**
     * Updates a {@code User} information
     *
     * @param user
     * @return success status
     */
    @Override
    public boolean update(User user) {
        return false;
    }

    /**
     * Method to get all users
     *
     * @return list of users
     */
    @Override
    public List<User> findAll() {
        return null;
    }

    /**
     * Method to get a {@code User} provided it's id
     *
     * @param id Unique id of user
     * @return the user
     */
    @Override
    public <T> T findById(int id) {
        return null;
    }

    /**
     * Method to get a list of {@code User} provided a name
     *
     * @param name Non unique name of the requested user
     * @return a list of users
     */
    @Override
    public List<User> findByName(int name) {
        return null;
    }
}