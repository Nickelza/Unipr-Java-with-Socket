package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WineDAO implements IOperations<Wine> {

    private Connection conn;
    private static WineDAO INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(WineDAO.class);

    public WineDAO() {
        conn = DBContext.getConnection();
    }

    public synchronized static WineDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new WineDAO();
        return INSTANCE;
    }

    @Override
    public void add(Wine wine) {
        PreparedStatement statement = null;
        Integer generatedId = null;
        try {

            StringTemplate INSERT_STATMENT =
                    new StringTemplate("INSERT INTO WINE (NAME, YEAR, PRODUCER, TECHNOTES) VALUES ('$NAME_WINE$', $YEAR_WINE$, '$PRODUCER_WINE$', '$TECHNOTES_WINE$')");

            INSERT_STATMENT.setAttribute("NAME_WINE", wine.getName());
            INSERT_STATMENT.setAttribute("YEAR_WINE", wine.getYear());
            INSERT_STATMENT.setAttribute("PRODUCER_WINE", wine.getProducer());
            INSERT_STATMENT.setAttribute("TECHNOTES_WINE", wine.getTechNotes());

            String SQL_INSERT = INSERT_STATMENT.toString();

            statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
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
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }


    /*

    public void delete(Wine wine) {
        Statement statement = null;
        try {
            statement = conn.createStatement();

            StringTemplate DELETE_STATMENT =
                    new StringTemplate("DELETE FROM WINE WHERE ID = $ID_WINE$");

            DELETE_STATMENT.setAttribute("$ID_WINE$", wine.getId());
            String SQL_DELETE = DELETE_STATMENT.toString();

            statement.executeUpdate(SQL_DELETE);

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
*/

    public List<Wine> findAll() {
        List<Wine> items = new ArrayList<>();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            String SQL_FIND_ALL = "SELECT * FROM REL_WINE_VINEYARD_EXTENDED ORDER BY WINE_ID";
            ResultSet rs = statement.executeQuery(SQL_FIND_ALL);
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
