package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Warehouse;
import it.unipr.ingegneria.entities.Wine;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WarehouseDAO implements IOperations<Warehouse> {

    private Connection conn;
    private static WarehouseDAO INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(WineDAO.class);

    public WarehouseDAO() {
        conn = DBContext.getConnection();
    }

    public synchronized static WarehouseDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new WarehouseDAO();
        return INSTANCE;
    }

    @Override
    public void add(Warehouse warehouse) {
        PreparedStatement statement = null;
        try {

            StringTemplate INSERT_STATMENT =
                    new StringTemplate("INSERT INTO WAREHOUSE (NAME) VALUES ('$NAME_WAREHOUSE$')");

            INSERT_STATMENT.setAttribute("NAME_WAREHOUSE", warehouse.getName());

            String SQL_INSERT = INSERT_STATMENT.toString();

            statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Integer generatedId = generatedKeys.getInt(1);
                        warehouse.setId(generatedId);
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
        String SQL_FIND_ALL = "SELECT * FROM REL_WINE_WAREHOUSE_EXTENDED ORDER BY WINE_ID";
        return this.buildWines(SQL_FIND_ALL);
    }

    public List<Wine> findByName(String name) {
        StringTemplate FIND_STATMENT =
                new StringTemplate("SELECT * FROM REL_WINE_WAREHOUSE_EXTENDED WHERE WINE_NAME LIKE '%$PARAM$%' ORDER BY WINE_ID");

        FIND_STATMENT.setAttribute("PARAM", name);
        String SQL_FIND_BY_NAME = FIND_STATMENT.toString();

        return this.buildWines(SQL_FIND_BY_NAME);
    }

    public List<Wine> findByYear(int year) {
        StringTemplate FIND_STATMENT =
                new StringTemplate("SELECT * FROM  REL_WINE_WAREHOUSE_EXTENDED WHERE WINE_YEAR = $PARAM$ ORDER BY WINE_ID");

        FIND_STATMENT.setAttribute("PARAM", year);
        String SQL_FIND_BY_YEAR = FIND_STATMENT.toString();

        return this.buildWines(SQL_FIND_BY_YEAR);
    }

    public Boolean checkAvailability(String wineName, Integer requiredQuantity) {
        Boolean hasEnoughWines = Boolean.FALSE;
        Statement statement = null;
        try {
            statement = conn.createStatement();

            StringTemplate COUNT_STATMENT =
                    new StringTemplate("SELECT COUNT(DISTINCT WINE_ID) FROM REL_WINE_WAREHOUSE_EXTENDED WHERE WINE_NAME = '$PARAM$' ORDER BY WINE_ID");

            COUNT_STATMENT.setAttribute("PARAM", wineName);

            String SQL_COUNT = COUNT_STATMENT.toString();
            ResultSet rs = statement.executeQuery(SQL_COUNT);
            int n = 0;
            if (rs.next()) {
                n = rs.getInt(1);
                if (n >= requiredQuantity) {
                    hasEnoughWines = Boolean.TRUE;
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
        return hasEnoughWines;
    }

    private List<Wine> buildWines(String sql) {
        List<Wine> items = new ArrayList<>();
        Statement statement = null;
        try {
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
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
