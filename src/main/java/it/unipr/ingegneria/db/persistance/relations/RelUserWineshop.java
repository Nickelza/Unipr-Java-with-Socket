package it.unipr.ingegneria.db.persistance.relations;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.persistance.WineDAO;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.user.User;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.log4j.Logger;

import java.sql.*;

public class RelUserWineshop {

    private Connection conn;
    private static RelUserWineshop INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(WineDAO.class);

    public RelUserWineshop() {
        conn = DBContext.getConnection();
    }


    public synchronized static RelUserWineshop getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RelUserWineshop();
        return INSTANCE;
    }

    public void add(User user, WineShop wineShop) {
        Statement statement = null;
        try {

            StringTemplate INSERT_STATMENT =
                    new StringTemplate("INSERT INTO REL_USER_WINESHOP (WINESHOP_ID, USER_ID) VALUES ($ID_WINESHOP$, $ID_USER$)");
            statement = conn.createStatement();

            INSERT_STATMENT.setAttribute("ID_USER", user.getId());
            INSERT_STATMENT.setAttribute("ID_WINESHOP", wineShop.getId());

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
