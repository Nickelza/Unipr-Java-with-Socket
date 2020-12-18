package it.unipr.ingegneria.db.persistance.relations;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.impl.Warehouse;
import it.unipr.ingegneria.impl.WineShop;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * The {@code RelWineshopWarehouse} handle the relation between a Wineshop entity and Warehouse entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class RelWineshopWarehouse {
    private Connection conn;
    private static RelWineshopWarehouse INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(RelWineshopWarehouse.class);

    /**
     * Return {@code RelWineshopWarehouse} singleton instance
     *
     */
    private RelWineshopWarehouse() {
        conn = DBContext.getConnection();
    }

    public synchronized static RelWineshopWarehouse getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RelWineshopWarehouse();
        return INSTANCE;
    }

    public Integer add(WineShop wineShop, Warehouse wareHouse) {
        {
            PreparedStatement preparedStatement = null;
            Integer generatedId = null;
            try {


                String INSERT_STATMENT = "INSERT INTO REL_WINESHOP_WAREHOUSE(ID_WAREHOUSE, ID_WINESHOP) VALUES (?, ?)";
                preparedStatement = conn.prepareStatement(INSERT_STATMENT, Statement.RETURN_GENERATED_KEYS);

                preparedStatement.setInt(1, wareHouse.getId());
                preparedStatement.setInt(2, wineShop.getId());

                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            generatedId = generatedKeys.getInt(1);
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
            return generatedId;
        }
    }
}
