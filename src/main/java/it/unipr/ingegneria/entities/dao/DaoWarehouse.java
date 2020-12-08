package it.unipr.ingegneria.entities.dao;

import it.unipr.ingegneria.api.IDaoGeneric;
import it.unipr.ingegneria.entities.Warehouse;
import it.unipr.ingegneria.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoWarehouse implements IDaoGeneric<Warehouse> {

    static Connection con = DatabaseConnection.getConnection();

    /**
     * Method to add a generic item
     *
     * @param warehouse
     * @return id of the added object
     */
    @Override
    public int add(Warehouse warehouse) throws SQLException {
        String query = "INSERT INTO Warehouse (name) VALUES (?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, warehouse.getName());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            return rs.getInt(1);
        }
        return -1;    }

    /**
     * Method to delete an item
     *
     * @param id Unique id of the searched element
     * @return
     */
    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM Warehouse WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }

    /**
     * Updates an item
     *
     * @param warehouse
     * @return success status
     */
    @Override
    public boolean update(Warehouse warehouse) throws SQLException {
        String query= "UPDATE Warehouse SET name = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, warehouse.getName());
        ps.setInt(2, (int) warehouse.getId());
        ps.executeUpdate();
        return true;
    }

    /**
     * Method to get all elements of an item
     *
     * @return a generic list of items
     */
    @Override
    public List<Warehouse> findAll() throws SQLException {
        String query = "SELECT * from Warehouse";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Warehouse> ls = new ArrayList();

        while (rs.next()) {
            Warehouse warehouse = new Warehouse(rs.getString("name"));
            ls.add(warehouse);
        }
        return ls;
    }

    /**
     * Method to get an item provided it's id
     *
     * @param id Unique id of the searched item
     * @return a generic item
     */
    @Override
    public Warehouse findById(int id) throws SQLException {
        String query = "SELECT * from Warehouse WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, String.valueOf(id));
        ResultSet rs = ps.executeQuery();
        Warehouse warehouse = null;

        while (rs.next()) {
            warehouse = new Warehouse(rs.getString("name"));
        }
        return warehouse;
    }

    /**
     * Method to get an item provided it's name
     *
     * @param name Non unique name of the requested item
     * @return a generic list of items
     */
    @Override
    public List<Warehouse> findByName(String name) throws SQLException {
        String query = "SELECT * from Warehouse WHERE name LIKE %?%";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        List<Warehouse> ls = new ArrayList();

        while (rs.next()) {
            Warehouse warehouse = new Warehouse(rs.getString("name"));
            ls.add(warehouse);
        }
        return ls;
    }
}
