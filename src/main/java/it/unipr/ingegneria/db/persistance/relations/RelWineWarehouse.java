package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.entities.Warehouse;
import it.unipr.ingegneria.entities.Wine;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RelWineWarehouse {
    private Connection conn;
    private static RelWineWarehouse INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(RelWineshopWarehouse.class);

    public RelWineWarehouse() {
        conn = DBContext.getConnection();
    }

    public synchronized static RelWineWarehouse getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RelWineWarehouse();
        return INSTANCE;
    }


    public void addAll(List<Wine> wines, Warehouse warehouse) {
        try {
            String SQL_INSERT =
                    "INSERT INTO REL_WINE_WAREHOUSE (WINE_ID, WAREHOUSE_ID) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            for (Wine wine : wines) {
                preparedStatement.setInt(1, wine.getId());
                preparedStatement.setInt(2, warehouse.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteAll(List<Wine> wines, Warehouse warehouse) {

        try {
            String SQL_INSERT =
                    "DELETE FROM REL_WINE_WAREHOUSE WHERE WINE_ID = ? AND WAREHOUSE_ID = ?";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            for (Wine wine : wines) {
                preparedStatement.setInt(1, wine.getId());
                preparedStatement.setInt(2, warehouse.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
