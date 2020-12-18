package it.unipr.ingegneria.db.persistance.relations;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.persistance.WineDAO;
import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.user.User;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * The {@code RelOrderUser} handle the relation between a Order entity and User entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class RelOrderUser {

    private Connection conn;
    private static RelOrderUser INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(WineDAO.class);

    private RelOrderUser() {
        conn = DBContext.getConnection();
    }

    /**
     * Return {@code RelOrderUser} singleton instance
     */
    public synchronized static RelOrderUser getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RelOrderUser();
        return INSTANCE;
    }

    /**
     * Insert the relation between order and user
     *
     * @param user  user of order
     * @param order order
     */
    public void add(User user, Order order) {
        PreparedStatement preparedStatement = null;
        try {
            String SQL_INSERT = "INSERT INTO REL_ORDER_USER (USER_ID, ORDER_ID) VALUES (?,?)";

            preparedStatement = conn.prepareStatement(SQL_INSERT);

            preparedStatement.setInt(1, user.getId());
            preparedStatement.setInt(2, order.getId());

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
