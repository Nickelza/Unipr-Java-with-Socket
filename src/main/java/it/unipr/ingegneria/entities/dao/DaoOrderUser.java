package it.unipr.ingegneria.entities.dao;

import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DaoOrderUser} manages database access for the {@code User} and {@code Order} relationship
 *
 * @see it.unipr.ingegneria.entities.user.User;
 * @see it.unipr.ingegneria.entities.Order
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class DaoOrderUser {
    static Connection con = DatabaseConnection.getConnection();

    /**
     * Method to add a User Order relationship
     *
     * @param user
     * @param order
     * @return success
     * @throws SQLException
     */
    public boolean add(User user, Order order) throws SQLException {
        String query = "INSERT INTO OrderUser (user_id, order_id) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, (int) user.getId());
        ps.setInt(2, (int) order.getID());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            return true;
        }
        return false;
    }

    /**
     * Method to get all the orders of User
     *
     * @param user_id Unique ID of the User
     * @return List of orders
     * @throws SQLException
     */
    public List<Order> findAll(int user_id, boolean delivered) throws SQLException {
        String query = "SELECT * from ViewOrderUser WHERE user_id = ? AND delivered = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, user_id);
        ps.setBoolean(2, delivered);
        ResultSet rs = ps.executeQuery();
        List<Order> ls = new ArrayList();

        while (rs.next()) {
            Order order = new Order();
            order.setID(rs.getInt("id"));
            order.setDate(rs.getDate("date"));
            order.setDelivered(rs.getBoolean("delivered"));
            DaoOrderWine daoOrderWine = new DaoOrderWine();
            order.setWine(daoOrderWine.findAll((int) order.getID()));
            ls.add(order);
        }
        return ls;
    }

}
