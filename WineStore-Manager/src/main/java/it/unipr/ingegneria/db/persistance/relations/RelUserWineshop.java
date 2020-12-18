package it.unipr.ingegneria.db.persistance.relations;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.persistance.WineDAO;
import it.unipr.ingegneria.impl.WineShop;
import it.unipr.ingegneria.entities.user.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code RelUserWineshop} handle the relation between a WineShop entity and User entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class RelUserWineshop {

    private Connection conn;
    private static RelUserWineshop INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(WineDAO.class);

    private RelUserWineshop() {
        conn = DBContext.getConnection();
    }

    /**
     * Return {@code RelUserWineshop} singleton instance
     *
     */
    public synchronized static RelUserWineshop getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RelUserWineshop();
        return INSTANCE;
    }

    public void add(User user, WineShop wineShop) {
        PreparedStatement preparedStatement = null;
        try {

            String INSERT_STATMENT = "INSERT INTO REL_USER_WINESHOP (WINESHOP_ID, USER_ID) VALUES (?, ?)";
            preparedStatement = conn.prepareStatement(INSERT_STATMENT);

            preparedStatement.setInt(1, wineShop.getId());
            preparedStatement.setInt(2, user.getId());

            preparedStatement.executeUpdate();


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
