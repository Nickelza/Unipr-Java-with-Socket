package it.unipr.ingegneria.entities.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.user.Customer;
import it.unipr.ingegneria.entities.user.Employee;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.api.IDaoGeneric;
import it.unipr.ingegneria.utils.DatabaseConnection;
import it.unipr.ingegneria.utils.Type;


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
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
        ps.setString(2, user.getSurname());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPassword());
        ps.setString(5, String.valueOf(user.getUserType()));
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }

    /**
     * Method to delete a {@code User}
     *
     * @param id Unique id of the searched user
     * @return
     */
    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM User WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }

    /**
     * Updates a {@code User} information
     *
     * @param user
     * @return
     */
    @Override
    public boolean update(User user) throws SQLException {
        String query= "UPDATE User SET name = ?, surname = ?, email = ?, password = ?, type = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, user.getName());
        ps.setString(2, user.getSurname());
        ps.setString(3, user.getEmail());
        ps.setString(4, user.getPassword());
        ps.setString(5, String.valueOf(user.getUserType()));
        ps.setInt(6, (int) user.getId());
        ps.executeUpdate();
        return true;
    }

    /**
     * Method to get all users
     *
     * @return list of users
     */
    @Override
    public List<User> findAll() throws SQLException {
        String query = "SELECT * from User";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<User> ls = new ArrayList();

        while (rs.next()) {
            User user;
            if (rs.getString("type").equals(Type.EMPLOYEE))
                user = new Employee();
            else
                user = new Customer();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            ls.add(user);
        }
        return ls;
    }

    /**
     * Overload method to get all users of a Type
     * @return
     * @throws SQLException
     */
    public List<User> findByType(Type type) throws SQLException {
        String query = "SELECT * from User WHERE type = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, type.name());
        ResultSet rs = ps.executeQuery();
        List<User> ls = new ArrayList();

        while (rs.next()) {
            User user;
            if (rs.getString("type").equals(Type.EMPLOYEE))
                user = new Employee();
            else
                user = new Customer();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            ls.add(user);
        }
        return ls;
    }


    /**
     *  Overload method to get all users of a Wineshop
     *
     * @param wineShop Wineshop in which to search
     * @return list of users
     */

    public List<User> findByWineShop(WineShop wineShop) throws SQLException {
        String query = "SELECT * from ViewWineShopUsers WHERE wineshop_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, (int) wineShop.getId());
        ResultSet rs = ps.executeQuery();
        List<User> ls = new ArrayList();

        while (rs.next()) {
            User user;
            if (rs.getString("type").equals(Type.EMPLOYEE))
                user = new Employee();
            else
                user = new Customer();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            ls.add(user);
        }
        return ls;
    }

    /**
     * Method to get a {@code User} provided it's id
     *
     * @param id Unique id of user
     * @return the user
     */
    @Override
    public User findById(int id) throws SQLException {
        String query = "SELECT * from User WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, String.valueOf(id));
        ResultSet rs = ps.executeQuery();
        User user = null;

        while (rs.next()) {
            if (rs.getString("type").equals(Type.EMPLOYEE))
                user = new Employee();
            else
                user = new Customer();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
        }
        return user;
    }

    /**
     * Method to get a list of {@code User} provided a name
     *
     * @param name Non unique name of the requested user
     * @return a list of users
     */
    @Override
    public List<User> findByName(String name) throws SQLException {
        String query = "SELECT * from User WHERE name LIKE %?%";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        List<User> ls = new ArrayList();

        while (rs.next()) {
            User user;
            if (rs.getString("type").equals(Type.EMPLOYEE))
                user = new Employee();
            else
                user = new Customer();

            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            ls.add(user);
        }
        return ls;
    }

    /**
     * Overload method to get a list of {@code User} provided a name
     *
     * @param wineShop Id of the Wineshop in which to search
     * @param name Non unique name of the requested user
     * @return a list of users
     */
    public List<User> findByName(WineShop wineShop, String name) throws SQLException {
        String query = "SELECT * from ViewWineShopUsers WHERE wineshop_id = ? AND name LIKE %?%";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, (int) wineShop.getId());
        ps.setString(2, name);
        ResultSet rs = ps.executeQuery();
        List<User> ls = new ArrayList();

        while (rs.next()) {
            User user;
            if (rs.getString("type").equals(Type.EMPLOYEE))
                user = new Employee();
            else
                user = new Customer();

            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));

            ls.add(user);
        }
        return ls;
    }

    public User login(String email, String password, WineShop wineShop) throws SQLException {
        String query = "SELECT * from ViewWineShopUsers WHERE wineshop_id = ? AND email = ? AND password = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, (int) wineShop.getId());
        ps.setString(2, email);
        ps.setString(3, password);
        ResultSet rs = ps.executeQuery();
        User user = null;

        while (rs.next()) {
            if (rs.getString("type").equals(Type.EMPLOYEE))
                user = new Employee();
            else
                user = new Customer();

            user.setId(rs.getInt("user_id"));
            user.setName(rs.getString("name"));
            user.setSurname(rs.getString("surname"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
        }

        return user;
    }
}