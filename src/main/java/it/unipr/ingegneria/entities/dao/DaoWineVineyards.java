package it.unipr.ingegneria.entities.dao;

import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import it.unipr.ingegneria.utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code DaoWineVineyards} manages database access for the {@code Wine} and {@code Vineyard} relationship
 *
 * @see it.unipr.ingegneria.entities.Wine
 * @see it.unipr.ingegneria.entities.Vineyard
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class DaoWineVineyards {
    static Connection con = DatabaseConnection.getConnection();

    /**
     * Method to add a Wine Vineyard relationship
     *
     * @param wine
     * @param vineyard
     * @return success
     * @throws SQLException
     */
    public boolean add(Wine wine, Vineyard vineyard) throws SQLException {
        String query = "INSERT INTO WineVineyards (wine_id, vineyard_id) VALUES (?, ?)";
        PreparedStatement ps = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, (int) wine.get_id());
        ps.setInt(2, (int) vineyard.getId());
        ps.executeUpdate();
        ResultSet rs = ps.getGeneratedKeys();
        if(rs.next()){
            return true;
        }
        return false;
    }

    /**
     * Method to find all Vineyards of a Wine
     *
     * @param wine_id Unique ID of the wine
     * @return list of Vineyards
     * @throws SQLException
     */
    public List<Vineyard> findAllVineyards(int wine_id) throws SQLException {
        String query = "SELECT * from ViewWineVineyard WHERE wine_id = ?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, wine_id);
        ResultSet rs = ps.executeQuery();
        List<Vineyard> ls = new ArrayList();

        while (rs.next()) {
            Vineyard vineyard = new Vineyard();
            vineyard.setId(rs.getInt("vineyard_id"));
            vineyard.setName(rs.getString("name"));

            ls.add(vineyard);
        }
        return ls;
    }

}
