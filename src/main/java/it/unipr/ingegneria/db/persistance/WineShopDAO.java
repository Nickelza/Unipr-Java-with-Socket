package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.db.persistance.relations.RelWineshopWarehouse;
import it.unipr.ingegneria.entities.WineShop;
import org.antlr.stringtemplate.StringTemplate;
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
            PreparedStatement statement = null;
            try {
                StringTemplate INSERT_STATMENT =
                        new StringTemplate("INSERT INTO WINESHOP (NAME) VALUES ('$WINESHOP_NAME$')");

                INSERT_STATMENT.setAttribute("WINESHOP_NAME", wineShop.getName());

                String SQL_INSERT = INSERT_STATMENT.toString();

                statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
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
                    statement.close();
                } catch (SQLException e) {
                    LOGGER.error(e);
                }
            }
        }
    }

}
