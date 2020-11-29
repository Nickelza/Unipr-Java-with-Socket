package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RelWineVineyard {
    private Connection conn;
    private static RelWineVineyard INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(RelWineshopWarehouse.class);

    public RelWineVineyard() {
        conn = DBContext.getConnection();
    }

    public synchronized static RelWineVineyard getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RelWineVineyard();
        return INSTANCE;
    }


    public void addAll(List<Wine> wines) {
        boolean itemsFound = false;
        try {
            String SQL_INSERT =
                    "INSERT INTO REL_WINE_VINEYARD (WINE_ID, VINEYARD_ID) VALUES (?, ?)";
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
            e.printStackTrace();
        }
    }
}
