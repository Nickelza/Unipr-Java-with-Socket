package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.db.persistance.relations.RelWineshopWarehouse;
import it.unipr.ingegneria.entities.Vineyard;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The {@code VineyardDAO} handle the persistance and retrival data of Vineyard entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class VineyardDAO implements IOperations<Vineyard> {

    private Connection conn;
    private static VineyardDAO INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(RelWineshopWarehouse.class);

    private VineyardDAO() {
        conn = DBContext.getConnection();
    }

    /**
     * Return {@code VineyardDAO} singleton instance
     */
    public synchronized static VineyardDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new VineyardDAO();
        return INSTANCE;
    }

    /**
     * Method to add the Vineyard in the database
     *
     * @param vineyard object
     */
    @Override
    public void add(Vineyard vineyard) {
        {
            PreparedStatement preparedStatement = null;

            try {
                String INSERT_STATMENT = "INSERT INTO VINEYARD (NAME) VALUES (?)";

                preparedStatement = conn.prepareStatement(INSERT_STATMENT, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, vineyard.getName());

                int affectedRows = preparedStatement.executeUpdate();
                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
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
                    preparedStatement.close();
                } catch (SQLException e) {
                    LOGGER.error(e);
                }
            }
        }


    }

    /**
     * Method to retrive all Vineyard
     *
     * @return List of Vineyard
     */
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
