package it.unipr.ingegneria.db.persistance.relations;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * The {@code RelWineVineyard} handle the relation between a Wineshop entity and Warehouse entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class RelWineVineyard {

    private Connection conn;
    private static RelWineVineyard INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(RelWineshopWarehouse.class);

    /**
     * Return {@code RelWineVineyard} singleton instance
     */
    public RelWineVineyard() {
        conn = DBContext.getConnection();
    }

    public synchronized static RelWineVineyard getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RelWineVineyard();
        return INSTANCE;
    }

    /**
     * Method to add in massive mode the wines to Wine Table.
     *
     * @param wines List
     */
    public void addAll(List<Wine> wines) {
        boolean itemsFound = false;
        try {
            String SQL_INSERT = "INSERT INTO REL_WINE_VINEYARD (WINE_ID, VINEYARD_ID) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            for (Wine wine : wines) {
                if (wine.getVineyards() != null && !wine.getVineyards().isEmpty()) {
                    List<Vineyard> itmes = wine.getVineyards();
                    itemsFound = true;
                    for (Vineyard vineyard : itmes) {
                        preparedStatement.setInt(1, wine.getId());
                        preparedStatement.setInt(2, vineyard.getId());
                        preparedStatement.addBatch();
                    }
                }
            }
            if (itemsFound)
                preparedStatement.executeBatch();

        } catch (SQLException e) {
            LOGGER.error(e);
        }
    }
}
