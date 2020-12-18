package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.impl.Warehouse;
import it.unipr.ingegneria.entities.Wine;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code  Warehouse} handle the persistance and retrival data of Warehouse entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class WarehouseDAO implements IOperations<Warehouse> {

    private Connection conn;
    private static WarehouseDAO INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(WineDAO.class);

    public WarehouseDAO() {
        conn = DBContext.getConnection();
    }

    /**
     * Return {@code WarehouseDAO} singleton instance
     */
    public synchronized static WarehouseDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new WarehouseDAO();
        return INSTANCE;
    }

    /**
     * Method to add the Warehouse in the database
     *
     * @param warehouse object
     */
    @Override
    public void add(Warehouse warehouse) {
        PreparedStatement preparedStatement = null;
        String INSERT_STATMENT = "INSERT INTO WAREHOUSE (NAME) VALUES (?)";
        try {
            preparedStatement = conn.prepareStatement(INSERT_STATMENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, warehouse.getName());


            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Integer generatedId = generatedKeys.getInt(1);
                        warehouse.setId(generatedId);
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
        String SQL_FIND_ALL = "SELECT * FROM REL_WINE_WAREHOUSE_EXTENDED ORDER BY WINE_ID";
        return this.buildWines(SQL_FIND_ALL);
    }

    /**
     * Method to retrieve all Wine not in the warehouse reading the database
     *
     * @return List of Wine Names not available
     */
    public List<String> findWineNotInWarehouse() {
        String sql = "SELECT DISTINCT(NAME) AS NAME FROM WINE t1 LEFT JOIN REL_WINE_WAREHOUSE_EXTENDED t2 ON t2.WINE_NAME = t1.NAME WHERE t2.WINE_NAME IS NULL";
        List<String> results = new ArrayList<>();
        Statement statement = null;
        try {
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                String WINE_NAME = rs.getString("NAME");
                results.add(WINE_NAME);
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
        return results;

    }

    /**
     * Method to check availability reading the database
     *
     * @param wineName         name of wine to search
     * @param requiredQuantity required quantity of wine
     * @return Boolean
     */
    public Boolean checkAvailability(String wineName, Integer requiredQuantity) {
        Boolean hasEnoughWines = Boolean.FALSE;
        String COUNT_STATMENT = "SELECT COUNT(DISTINCT WINE_ID) FROM REL_WINE_WAREHOUSE_EXTENDED WHERE WINE_NAME = ? ORDER BY WINE_ID";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(COUNT_STATMENT);
            preparedStatement.setString(1, wineName);

            ResultSet rs = preparedStatement.executeQuery();
            int n = 0;
            if (rs.next()) {
                n = rs.getInt(1);
                if (n >= requiredQuantity) {
                    hasEnoughWines = Boolean.TRUE;
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
        return hasEnoughWines;
    }

    /**
     * Build the object executing the passed query
     *
     * @param sql the executed query
     * @return List of Wine
     */
    private List<Wine> buildWines(String sql) {
        List<Wine> items = new ArrayList<>();
        Statement statement = null;
        try {
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Wine wine = Wine.valueOf(rs);
                // Check if there is element with same WINE_ID, if present add object update only Vineyards Array
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
