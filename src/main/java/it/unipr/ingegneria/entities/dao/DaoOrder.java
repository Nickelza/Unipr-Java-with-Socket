package it.unipr.ingegneria.entities.dao;

import it.unipr.ingegneria.api.IDaoGeneric;
import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DaoOrder implements IDaoGeneric<Order> {

    static Connection con = DatabaseConnection.getConnection();

    /**
     * Method to add a generic item
     *
     * @param order
     * @return id of the added object
     */
    @Override
    public int add(Order order) throws SQLException {
        return 0;
    }

    /**
     * Overlaod method to add an order
     *
     * @param order
     * @param user
     * @return success
     */
    public boolean add(Order order, User user) throws SQLException {
        String query = "INSERT INTO Order (date, delivered) VALUES (?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setDate(1, (Date) order.getDate());
        ps.setBoolean(1, order.isDelivered());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            int orderID = rs.getInt(1);
            order.setID(orderID);
            DaoOrderUser daoOrderUser = new DaoOrderUser();
            daoOrderUser.add(user, order);

            DaoOrderWine daoOrderWine = new DaoOrderWine();
            Iterator<Wine> wineIterator = order.getWine().iterator();
            while (wineIterator.hasNext()) {
                daoOrderWine.add(wineIterator.next(), order);
            }
            return true;
        }
        return false;
    }

    /**
     * Method to delete an item
     *
     * @param id Unique id of the searched element
     * @return
     */
    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM Order WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }

    /**
     * Updates an item
     *
     * @param order
     * @return success status
     */
    @Override
    public boolean update(Order order) throws SQLException {
        return true;
    }

    /**
     * Method to get all elements of an item
     *
     * @return a generic list of items
     */
    @Override
    public List<Order> findAll() throws SQLException {
        return null;
    }

    /**
     * Method to get an item provided it's id
     *
     * @param id Unique id of the searched item
     * @return a generic item
     */
    @Override
    public Order findById(int id) throws SQLException {
        return null;
    }

    /**
     * Method to get an item provided it's name
     *
     * @param name Non unique name of the requested item
     * @return a generic list of items
     */
    @Override
    public List<Order> findByName(String name) throws SQLException {
        return null;
    }

    /**
     * Method to get orders filtered by delivery
     *
     * @param delivered
     * @return list of Orders
     * @throws SQLException
     */
    public List<Order> findDelivered(boolean delivered) throws SQLException {
        String query = "SELECT * from Order WHERE delivered = ? ORDER BY date DESC";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setBoolean(1, delivered);
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

    public boolean deliverOrder(Order order) throws SQLException {
        String query= "UPDATE Order SET delivered = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, (int) order.getID());
        ps.executeUpdate();
        return true;
    }
}
