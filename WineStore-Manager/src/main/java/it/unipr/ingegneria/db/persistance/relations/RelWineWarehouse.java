package it.unipr.ingegneria.db.persistance.relations;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.impl.Warehouse;
import it.unipr.ingegneria.entities.Wine;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * The {@code RelWineWarehouse} handle the relation between a Wine entity and Warehouse entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class RelWineWarehouse {

    private Connection conn;
    private static RelWineWarehouse INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(RelWineshopWarehouse.class);

    private RelWineWarehouse() {
        conn = DBContext.getConnection();
    }

    /**
     * Return {@code RelWineVineyard} singleton instance
     *
     */
    public synchronized static RelWineWarehouse getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RelWineWarehouse();
        return INSTANCE;
    }


    public void addAll(List<Wine> wines, Warehouse warehouse) {
        try {
            String SQL_INSERT = "INSERT INTO REL_WINE_WAREHOUSE (WINE_ID, WAREHOUSE_ID) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            for (Wine wine : wines) {
                preparedStatement.setInt(1, wine.getId());
                preparedStatement.setInt(2, warehouse.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }


    public void deleteAll(List<Wine> wines, Warehouse warehouse) {

        try {
            String SQL_INSERT = "DELETE FROM REL_WINE_WAREHOUSE WHERE WINE_ID = ? AND WAREHOUSE_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            for (Wine wine : wines) {
                preparedStatement.setInt(1, wine.getId());
                preparedStatement.setInt(2, warehouse.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            LOGGER.info(e);
        }
    }
}
