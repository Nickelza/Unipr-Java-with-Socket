package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.entities.WineShop;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.entities.user.UserFactory;
import it.unipr.ingegneria.utils.Type;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * The {@code UserDAO} handle the persistance and retrival data of User entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class UserDAO implements IOperations<User> {
    private Connection conn;
    private static UserDAO INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(WineDAO.class);

    public UserDAO() {
        conn = DBContext.getConnection();
    }

    public synchronized static UserDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserDAO();
        return INSTANCE;
    }


    @Override
    public void add(User user) {
        PreparedStatement statement = null;
        Integer generatedId = null;
        try {
            StringTemplate INSERT_STATMENT =
                    new StringTemplate("INSERT INTO USER(NAME, SURNAME, EMAIL, PASSWORD, TYPE)" +
                            " VALUES ('$USER_NAME$', '$USER_SURNAME$', '$USER_EMAIL$', '$USER_PASSWORD$', '$USER_TYPE$')");

            INSERT_STATMENT.setAttribute("USER_NAME", user.getName());
            INSERT_STATMENT.setAttribute("USER_SURNAME", user.getSurname());
            INSERT_STATMENT.setAttribute("USER_EMAIL", user.getEmail());
            INSERT_STATMENT.setAttribute("USER_PASSWORD", user.getPassword());
            INSERT_STATMENT.setAttribute("USER_TYPE", user.getUserType().toString());

            String SQL_INSERT = INSERT_STATMENT.toString();

            statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        user.setId(generatedId);
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


    public List<User> findAll(WineShop wineShop) {
        StringTemplate SELECT_STATEMENT =
                new StringTemplate("SELECT * FROM REL_USER_WINESHOP_EXTENDED u WHERE u.WINESHOP_ID = $ID_WINESHOP$ ORDER BY USER_ID");
        SELECT_STATEMENT.setAttribute("ID_WINESHOP", wineShop.getId());

        String SQL_FIND_ALL = SELECT_STATEMENT.toString();
        return buildUsers(SQL_FIND_ALL);
    }


    public User executeLogin(String userEmail, String userPassword, WineShop wineShop) {


        StringTemplate SELECT_STATEMENT =
                new StringTemplate("SELECT * FROM REL_USER_WINESHOP_EXTENDED u WHERE u.EMAIL = '$USER_MAIL$' AND u.PASSWORD = '$USER_PASSWORD$' AND u.WINESHOP_ID = $ID_WINESHOP$ LIMIT 1");

        SELECT_STATEMENT.setAttribute("USER_MAIL", userEmail);
        SELECT_STATEMENT.setAttribute("USER_PASSWORD", userPassword);
        SELECT_STATEMENT.setAttribute("ID_WINESHOP", wineShop.getId());

        String SQL_SELECT = SELECT_STATEMENT.toString();

        return buildUsers(SQL_SELECT).get(0);


    }

    private List<User> buildUsers(String sql) {
        List<User> users = new ArrayList<>();
        Statement statement = null;

        try {
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                String USER_TYPE = rs.getString("TYPE");
                User u = UserFactory.getUser(Type.valueOf(USER_TYPE));
                Integer USER_ID = rs.getInt("USER_ID");
                String USER_NAME = rs.getString("NAME");
                String USER_SURNAME = rs.getString("SURNAME");
                String USER_EMAIL = rs.getString("EMAIL");
                String USER_PASSWORD = rs.getString("PASSWORD");
                if (USER_ID != null)
                    u.setId(USER_ID)
                            .setName(USER_NAME)
                            .setSurname(USER_SURNAME)
                            .setEmail(USER_EMAIL)
                            .setPassword(USER_PASSWORD);
                users.add(u);
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
        return users;
    }


}
