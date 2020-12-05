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

/**
 * The {@code WineDAO} handle the persistance and retrival data of Wine entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
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

    public List<Wine> findAll() {
        String SQL_FIND_BY_NAME = "SELECT * FROM REL_WINE_VINEYARD_EXTENDED";
        return this.buildWines(SQL_FIND_BY_NAME);
    }

    public List<Wine> findByName(String name) {
        StringTemplate FIND_STATMENT =
                new StringTemplate("SELECT * FROM REL_WINE_VINEYARD_EXTENDED WHERE WINE_NAME LIKE '%$PARAM$%' ORDER BY WINE_ID");

        FIND_STATMENT.setAttribute("PARAM", name);
        String SQL_FIND_BY_NAME = FIND_STATMENT.toString();

        return this.buildWines(SQL_FIND_BY_NAME);
    }

    public List<Wine> findByYear(int year) {
        StringTemplate FIND_STATMENT =
                new StringTemplate("SELECT * FROM  REL_WINE_VINEYARD_EXTENDED WHERE WINE_YEAR = $PARAM$ ORDER BY WINE_ID");

        FIND_STATMENT.setAttribute("PARAM", year);
        String SQL_FIND_BY_YEAR = FIND_STATMENT.toString();

        return this.buildWines(SQL_FIND_BY_YEAR);
    }

    private List<Wine> buildWines(String sql) {
        List<Wine> items = new ArrayList<>();
        Statement statement = null;
        try {
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Wine wine = Wine.valueOf(rs);
                // Check if there is element with same WINE_ID, if present add object updating only Vineyards List
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
