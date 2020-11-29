package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.entities.Warehouse;
import it.unipr.ingegneria.entities.WineShop;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.log4j.Logger;

import java.sql.*;

public class RelWineshopWarehouse {
    private Connection conn;
    private static RelWineshopWarehouse INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(RelWineshopWarehouse.class);

    public RelWineshopWarehouse() {
        conn = DBContext.getConnection();
    }

    public synchronized static RelWineshopWarehouse getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RelWineshopWarehouse();
        return INSTANCE;
    }

    public Integer add(WineShop wineShop, Warehouse wareHouse) {
        {
            PreparedStatement statement = null;
            Integer generatedId = null;
            try {


                StringTemplate INSERT_STATMENT =
                        new StringTemplate("INSERT INTO REL_WINESHOP_WAREHOUSE(ID_WAREHOUSE, ID_WINESHOP) VALUES ($WAREHOUSE_ID$, $WINESHOP_ID$)");

                INSERT_STATMENT.setAttribute("WAREHOUSE_ID", wareHouse.getId());
                INSERT_STATMENT.setAttribute("WINESHOP_ID", wineShop.getId());

                String SQL_INSERT = INSERT_STATMENT.toString();

                statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            generatedId = generatedKeys.getInt(1);
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
            return generatedId;
        }
    }
}
