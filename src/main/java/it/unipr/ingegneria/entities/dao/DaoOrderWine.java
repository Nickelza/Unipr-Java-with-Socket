package it.unipr.ingegneria.entities.dao;

import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DaoOrderWine} manages database access for the {@code Wine} and {@code Order} relationship
 *
 * @see it.unipr.ingegneria.entities.Wine
 * @see it.unipr.ingegneria.entities.Order
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class DaoOrderWine {
    static Connection con = DatabaseConnection.getConnection();

    /**
     * Method to add a Wine Order relationship
     *
     * @param wine
     * @param order
     * @return success
     * @throws SQLException
     */
    public boolean add(Wine wine, Order order) throws SQLException {
        String query = "INSERT INTO OrderWine (wine_id, order_id) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, (int) wine.get_id());
        ps.setInt(2, (int) order.getID());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            return true;
        }
        return false;
    }

    /**
     * Method to find all Wines in an order
     *
     * @param order_id Unique ID of the order
     * @return list of Wines
     * @throws SQLException
     */
    public List<Wine> findAll(int order_id) throws SQLException {
        String query = "SELECT * from ViewOrderWine WHERE order_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, order_id);
        ResultSet rs = ps.executeQuery();
        List<Wine> ls = new ArrayList();

        while (rs.next()) {
            Wine wine = new Wine();
            wine.set_id(rs.getInt("wine_id"));
            wine.setName(rs.getString("name"));
            wine.setYear(rs.getDate("year"));
            wine.setProducer(rs.getString("producer"));
            wine.setTechNotes(rs.getString("tech_note"));

            ls.add(wine);
        }
        return ls;
    }

}
