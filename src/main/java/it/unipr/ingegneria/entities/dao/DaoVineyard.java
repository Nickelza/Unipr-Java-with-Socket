package it.unipr.ingegneria.entities.dao;

import it.unipr.ingegneria.api.IDaoGeneric;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoVineyard implements IDaoGeneric<Vineyard> {

    static Connection con = DatabaseConnection.getConnection();

    /**
     * Method to add a generic item
     *
     * @param vineyard
     * @return id of the added object
     */
    @Override
    public int add(Vineyard vineyard) throws SQLException {
        String query = "INSERT INTO Vineyard (name) VALUES (?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, vineyard.getName());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }

    /**
     * Method to delete an item
     *
     * @param id Unique id of the searched element
     * @return
     */
    @Override
    public boolean delete(int id) throws SQLException {
        String query = "DELETE FROM Vineyard WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }

    /**
     * Updates an item
     *
     * @param vineyard
     * @return success status
     */
    @Override
    public boolean update(Vineyard vineyard) throws SQLException {
        String query= "UPDATE Vineyard SET name = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, vineyard.getName());
        ps.setInt(2, (int) vineyard.getId());
        ps.executeUpdate();
        return true;
    }

    /**
     * Method to get all elements of an item
     *
     * @return a generic list of items
     */
    @Override
    public List<Vineyard> findAll() throws SQLException {
        String query = "SELECT * from Vineyard";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Vineyard> ls = new ArrayList();

        while (rs.next()) {
            Vineyard vineyard = new Vineyard(rs.getString("name"));
            ls.add(vineyard);
        }
        return ls;    }

    /**
     * Method to get an item provided it's id
     *
     * @param id Unique id of the searched item
     * @return a generic item
     */
    @Override
    public Vineyard findById(int id) throws SQLException {
        String query = "SELECT * from Vineyard WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, String.valueOf(id));
        ResultSet rs = ps.executeQuery();
        Vineyard vineyard = null;

        while (rs.next()) {
            vineyard = new Vineyard(rs.getString("name"));
        }
        return vineyard;
    }

    /**
     * Method to get an item provided it's name
     *
     * @param name Non unique name of the requested item
     * @return a generic list of items
     */
    @Override
    public List<Vineyard> findByName(String name) throws SQLException {
        String query = "SELECT * from Vineyard WHERE name LIKE %?%";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        List<Vineyard> ls = new ArrayList();

        while (rs.next()) {
            Vineyard vineyard = new Vineyard(rs.getString("name"));
            ls.add(vineyard);
        }
        return ls;    }
}
