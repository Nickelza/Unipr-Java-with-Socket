package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.db.persistance.relations.RelWineshopWarehouse;
import it.unipr.ingegneria.entities.Vineyard;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VineyardDAO implements IOperations<Vineyard> {

    private Connection conn;
    private static VineyardDAO INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(RelWineshopWarehouse.class);

    public VineyardDAO() {
        conn = DBContext.getConnection();
    }

    public synchronized static VineyardDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new VineyardDAO();
        return INSTANCE;
    }

    @Override
    public void add(Vineyard vineyard) {
        {
            PreparedStatement statement = null;

            try {
                StringTemplate INSERT_STATMENT =
                        new StringTemplate("INSERT INTO VINEYARD (NAME) VALUES ('$VINEYARD_NAME$')");

                INSERT_STATMENT.setAttribute("VINEYARD_NAME", vineyard.getName());

                String SQL_INSERT = INSERT_STATMENT.toString();

                statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

                int affectedRows = statement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            Integer generatedId = generatedKeys.getInt(1);
                            vineyard.setId(generatedId);
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

    public List<Vineyard> findAll() {
        List<Vineyard> items = new ArrayList<>();
        String SQL_SELECT = "SELECT * FROM VINEYARD";
        Statement statement = null;
        try {
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(SQL_SELECT);
            while (rs.next()) {
                Vineyard vineyard = Vineyard.valueOf(rs);
                items.add(vineyard);
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
