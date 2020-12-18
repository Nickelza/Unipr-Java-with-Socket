package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code WineDAO} handle the persistance and retrival data of Wine entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class WineDAO implements IOperations<Wine> {

    private Connection conn;
    private static WineDAO INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(WineDAO.class);

    private WineDAO() {
        conn = DBContext.getConnection();
    }

    /**
     * Return {@code WineDAO} singleton instance
     */
    public synchronized static WineDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new WineDAO();
        return INSTANCE;
    }

    /**
     * Method to add the Wine in the database
     *
     * @param wine object
     */
    @Override
    public void add(Wine wine) {
        PreparedStatement preparedStatement = null;
        Integer generatedId = null;
        try {

            String INSERT_STATMENT = "INSERT INTO WINE (NAME, YEAR, PRODUCER, TECHNOTES) VALUES (?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(INSERT_STATMENT, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, wine.getName());
            preparedStatement.setInt(2, wine.getYear());
            preparedStatement.setString(3, wine.getProducer());
            preparedStatement.setString(4, wine.getTechNotes());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        wine.setId(generatedId);
                    }
                }
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    /**
     * Method to retrive all Wine
     *
     * @return List of Wine
     */
    public List<Wine> findAll() {
        String SQL_FIND_BY_NAME = "SELECT * FROM REL_WINE_VINEYARD_EXTENDED";
        List<Wine> items = new ArrayList<>();
        Statement statement = null;
        try {
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(SQL_FIND_BY_NAME);
            while (rs.next()) {
                Wine wine = Wine.valueOf(rs);
                // Check if there is element with same WINE_ID, if present add object updating only Vineyards List
                if (items.contains(wine)) {
                    if ((wine.getVineyards() != null && !wine.getVineyards().isEmpty())) {
                        Vineyard extracted = wine.getVineyards().get(0);
                        int position = items.indexOf(wine);
                        Wine oldWine = items.get(position);
                        oldWine.getVineyards().add(extracted);
                    }
                } else {
                    items.add(wine);
                }

            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return items;
    }


    /**
     * Method to retrive all Wine by a given name
     *
     * @param name name of searched wine
     * @return List of Wine
     */
    public List<Wine> findByName(String name) {
        String FIND_STATMENT = "SELECT * FROM REL_WINE_VINEYARD_EXTENDED WHERE WINE_NAME = ? ORDER BY WINE_ID";
        List<Wine> items = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(FIND_STATMENT);
            preparedStatement.setString(1, name);

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Wine wine = Wine.valueOf(rs);
                // Check if there is element with same WINE_ID, if present add object updating only Vineyards List
                if (items.contains(wine)) {
                    if ((wine.getVineyards() != null && !wine.getVineyards().isEmpty())) {
                        Vineyard extracted = wine.getVineyards().get(0);
                        int position = items.indexOf(wine);
                        Wine oldWine = items.get(position);
                        oldWine.getVineyards().add(extracted);
                    }
                } else {
                    items.add(wine);
                }

            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return items;
    }

    /**
     * Method to retrive all Wine by a given name
     *
     * @param year year of searched wine
     * @return List of Wine
     */
    public List<Wine> findByYear(int year) {
        String FIND_STATMENT = "SELECT * FROM REL_WINE_VINEYARD_EXTENDED WHERE WINE_YEAR = ? ORDER BY WINE_ID";
        List<Wine> items = new ArrayList<>();
        PreparedStatement statement = null;
        try {
            statement = conn.prepareStatement(FIND_STATMENT);
            statement.setInt(1, year);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Wine wine = Wine.valueOf(rs);
                // Check if there is element with same WINE_ID, if present add object updating only Vineyards List
                if (items.contains(wine)) {
                    if ((wine.getVineyards() != null && !wine.getVineyards().isEmpty())) {
                        Vineyard extracted = wine.getVineyards().get(0);
                        int position = items.indexOf(wine);
                        Wine oldWine = items.get(position);
                        oldWine.getVineyards().add(extracted);
                    }
                } else {
                    items.add(wine);
                }

            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
        return items;
    }


}
