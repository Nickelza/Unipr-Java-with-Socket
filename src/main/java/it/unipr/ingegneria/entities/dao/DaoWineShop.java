package it.unipr.ingegneria.entities.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.api.IDaoGeneric;
import it.unipr.ingegneria.utils.DatabaseConnection;

/**
 * The {@code DaoUser} manages database access for the {@code Wineshop} entity
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class DaoWineShop implements IDaoGeneric<WineShop> {

    static Connection con = DatabaseConnection.getConnection();


    /**
     * Method to add a WineShop
     *
     * @param wineShop
     * @return id of the added object
     */
    @Override
    public int add(WineShop wineShop) throws SQLException {
        String query = "INSERT INTO WineShop (name) VALUES (?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, wineShop.getName());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }

    /**
     * Method to delete a WineShop
     *
     * @param id Unique id of the searched WineShop
     * @return
     */
    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM WineShop WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }

    /**
     * Updates a WineShop
     *
     * @param wineShop
     * @return success status
     */
    @Override
    public boolean update(WineShop wineShop) throws SQLException {
        String query= "UPDATE WineShop SET name = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, wineShop.getName());
        ps.setInt(2, (int) wineShop.getId());
        ps.executeUpdate();
        return true;
    }

    /**
     * Method to get all WineShops
     *
     * @return a list of WineShops
     */
    @Override
    public List<WineShop> findAll() throws SQLException {
        String query = "SELECT * from WineShop";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<WineShop> ls = new ArrayList();

        while (rs.next()) {
            WineShop wineShop = new WineShop(rs.getString("name"));
            ls.add(wineShop);
        }
        return ls;
    }

    /**
     * Method to get a WineShop provided it's id
     *
     * @param id Unique id of the WineShop
     * @return a WineShop
     */
    @Override
    public WineShop findById(int id) throws SQLException {
        String query = "SELECT * from WineShop WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, String.valueOf(id));
        ResultSet rs = ps.executeQuery();
        WineShop wineShop = null;

        while (rs.next()) {
            wineShop = new WineShop(rs.getString("name"));
        }
        return wineShop;
    }

    /**
     * Method to get an WineShop provided it's name
     *
     * @param name Non unique name of the requested WineShop
     * @return a generic list of WineShops
     */
    @Override
    public List<WineShop> findByName(String name) throws SQLException {
        String query = "SELECT * from WineShop WHERE name LIKE %?%";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        List<WineShop> ls = new ArrayList();

        while (rs.next()) {
            WineShop wineShop = new WineShop(rs.getString("name"));
            ls.add(wineShop);
        }
        return ls;
    }
}
