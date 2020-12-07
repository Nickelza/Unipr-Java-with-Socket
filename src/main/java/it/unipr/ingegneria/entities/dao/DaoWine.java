package it.unipr.ingegneria.entities.dao;

import it.unipr.ingegneria.api.IDaoGeneric;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoWine implements IDaoGeneric<Wine> {

    static Connection con = DatabaseConnection.getConnection();

    /**
     * Method to add a wine
     *
     * @param wine
     * @return id of the added object
     */
    @Override
    public int add(Wine wine) throws SQLException {
        String query = "INSERT INTO Wine (name, year, producer, tech_note) VALUES (?, ?, ?, ?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, wine.getName());
        ps.setDate(2, (Date) wine.getYear());
        ps.setString(3, wine.getProducer());
        ps.setString(4, wine.getTechNotes());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            return rs.getInt(1);
        }
        return -1;
    }

    /**
     * Method to add a wine in quantity
     *
     * @param wine
     * @param quantity
     * @return id of the added object
     */
    public boolean add(Wine wine, int quantity) throws SQLException {
        String query = "INSERT INTO Wine (name, year, producer, tech_note) VALUES (?, ?, ?, ?)" +
                "ON DUPLICATE KEY UPDATE quantity = ?";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, wine.getName());
        ps.setDate(2, (Date) wine.getYear());
        ps.setString(3, wine.getProducer());
        ps.setString(4, wine.getTechNotes());
        ps.setInt(5, quantity);
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
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
        String query = "DELETE FROM Wine WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ps.executeUpdate();
        return true;
    }

    /**
     * Updates an item
     *
     * @param wine
     * @return success status
     */
    @Override
    public boolean update(Wine wine) throws SQLException {
        String query= "UPDATE User SET name = ?, year = ?, producer = ?, tech_note = ? WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, wine.getName());
        ps.setDate(2, (Date) wine.getYear());
        ps.setString(3, wine.getProducer());
        ps.setString(4, wine.getTechNotes());
        ps.setInt(5, (int) wine.get_id());
        ps.executeUpdate();
        return true;
    }

    /**
     * Method to get all elements of an item
     *
     * @return a generic list of items
     */
    @Override
    public List<Wine> findAll() throws SQLException {
        String query = "SELECT * from Wine";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Wine> ls = new ArrayList();

        while (rs.next()) {
            Wine wine = new Wine();
            wine.set_id(rs.getInt("id"));
            wine.setName(rs.getString("name"));
            wine.setYear(rs.getDate("year"));
            wine.setProducer(rs.getString("producer"));
            wine.setTechNotes(rs.getString("tech_note"));

            ls.add(wine);
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
    public Wine findById(int id) throws SQLException {
        String query = "SELECT * from Wine WHERE id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        Wine wine = null;
        while (rs.next()) {
            wine = new Wine();
            wine.set_id(rs.getInt("id"));
            wine.setName(rs.getString("name"));
            wine.setYear(rs.getDate("year"));
            wine.setProducer(rs.getString("producer"));
            wine.setTechNotes(rs.getString("tech_note"));
        }
        return wine;
    }

    /**
     * Method to get an item provided it's name
     *
     * @param name Non unique name of the requested item
     * @return a generic list of items
     */
    @Override
    public List<Wine> findByName(String name) throws SQLException {
        String query = "SELECT * from Wine WHERE name LIKE %?%";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        List<Wine> ls = new ArrayList();

        while (rs.next()) {
            Wine wine = new Wine();
            wine.set_id(rs.getInt("id"));
            wine.setName(rs.getString("name"));
            wine.setYear(rs.getDate("year"));
            wine.setProducer(rs.getString("producer"));
            wine.setTechNotes(rs.getString("tech_note"));

            ls.add(wine);
        }
        return ls;
    }

    /**
     * Method to search for Wines by year
     *
     * @param year
     * @return a list of Wines
     * @throws SQLException
     */
    public List<Wine> findByYear(int year) throws SQLException {
        String query = "SELECT * from Wine WHERE year = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, year);
        ResultSet rs = ps.executeQuery();
        List<Wine> ls = new ArrayList();

        while (rs.next()) {
            Wine wine = new Wine();
            wine.set_id(rs.getInt("id"));
            wine.setName(rs.getString("name"));
            wine.setYear(rs.getDate("year"));
            wine.setProducer(rs.getString("producer"));
            wine.setTechNotes(rs.getString("tech_note"));

            ls.add(wine);
        }
        return ls;
    }

    /**
     * Method to search for Wines where quantity > 0
     *
     * @return a list of Wines
     * @throws SQLException
     */
    public List<Wine> findAvailable() throws SQLException {
        String query = "SELECT * from Wine WHERE quantity >= 1";
        PreparedStatement ps = con.prepareStatement(query);
        ResultSet rs = ps.executeQuery();
        List<Wine> ls = new ArrayList();

        while (rs.next()) {
            Wine wine = new Wine();
            wine.set_id(rs.getInt("id"));
            wine.setName(rs.getString("name"));
            wine.setYear(rs.getDate("year"));
            wine.setProducer(rs.getString("producer"));
            wine.setTechNotes(rs.getString("tech_note"));

            ls.add(wine);
        }
        return ls;
    }
}
