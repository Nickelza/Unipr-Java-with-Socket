package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.db.persistance.relations.RelWineshopWarehouse;
import it.unipr.ingegneria.entities.WineShop;
import org.apache.log4j.Logger;

import java.sql.*;

/**
 * The {@code WineShopDAO} handle the persistance and retrival data of WineShop entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class WineShopDAO implements IOperations<WineShop> {
    private Connection conn;
    private static WineShopDAO INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(RelWineshopWarehouse.class);

    public WineShopDAO() {
        conn = DBContext.getConnection();
    }

    public synchronized static WineShopDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new WineShopDAO();
        return INSTANCE;
    }

    @Override
    public void add(WineShop wineShop) {
        {
            PreparedStatement preparedStatement = null;
            try {
                String SQL_INSERT = "INSERT INTO WINESHOP (NAME) VALUES (?)";
                preparedStatement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, wineShop.getName());


                int affectedRows  = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            Integer generatedId = generatedKeys.getInt(1);
                            wineShop.setId(generatedId);
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
    }

}
