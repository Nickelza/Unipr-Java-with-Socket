package it.unipr.ingegneria.db.persistance.relations;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.persistance.WineDAO;
import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.user.User;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The {@code RelOrderUser} handle the relation between a Order entity and User entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class RelOrderUser {

    private Connection conn;
    private static RelOrderUser INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(WineDAO.class);

    public RelOrderUser() {
        conn = DBContext.getConnection();
    }


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
        Statement statement = null;
        try {

            StringTemplate INSERT_STATMENT =
                    new StringTemplate("INSERT INTO REL_ORDER_USER (USER_ID, ORDER_ID) VALUES ($ID_USER$, $ID_ORDER$)");
            statement = conn.createStatement();

            INSERT_STATMENT.setAttribute("ID_USER", user.getId());
            INSERT_STATMENT.setAttribute("ID_ORDER", order.getId());

            String SQL_INSERT = INSERT_STATMENT.toString();
            statement.executeUpdate(SQL_INSERT);


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
