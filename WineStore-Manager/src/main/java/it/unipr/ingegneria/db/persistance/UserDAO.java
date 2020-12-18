package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.impl.WineShop;
import it.unipr.ingegneria.entities.user.User;
import it.unipr.ingegneria.utils.UserFactory;
import it.unipr.ingegneria.utils.Type;
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

    private UserDAO() {
        conn = DBContext.getConnection();
    }

    /**
     * Return {@code UserDAO} singleton instance
     */
    public synchronized static UserDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new UserDAO();
        return INSTANCE;
    }

    /**
     * Method to add the User in the database
     *
     * @param user object
     */
    @Override
    public void add(User user) {
        PreparedStatement preparedStatement = null;
        Integer generatedId;
        try {

            String INSERT_STATMENT = "INSERT INTO USER(NAME, SURNAME, EMAIL, PASSWORD, TYPE) VALUES (?, ?, ?, ?, ?)";
            preparedStatement = conn.prepareStatement(INSERT_STATMENT, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getUserType().toString());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
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
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }

    }

    /**
     * Method to retrive all User
     *
     * @return List of User
     */
    public List<User> findAll(WineShop wineShop) {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String SELECT_STATEMENT = "SELECT * FROM REL_USER_WINESHOP_EXTENDED u WHERE u.WINESHOP_ID = ? ORDER BY USER_ID";
        try {
            preparedStatement = conn.prepareStatement(SELECT_STATEMENT);
            preparedStatement.setInt(1, wineShop.getId());
            ResultSet rs = preparedStatement.executeQuery();
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
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }

        return users;

    }


    /**
     * Method to retrive all User by type in the wineshop
     *
     * @param wineShop object
     * @param type     of the searched user
     * @return List of User
     */
    public List<User> findByType(WineShop wineShop, String type) {
        List<User> users = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        String SELECT_STATEMENT = "SELECT * FROM REL_USER_WINESHOP_EXTENDED u WHERE u.WINESHOP_ID = ? AND u.TYPE = ? ORDER BY USER_ID";
        try {
            preparedStatement = conn.prepareStatement(SELECT_STATEMENT);
            preparedStatement.setInt(1, wineShop.getId());
            preparedStatement.setString(2, type);
            ResultSet rs = preparedStatement.executeQuery();
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
                preparedStatement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }

        return users;

    }

    /**
     * Method to try to execute login
     *
     * @param userEmail    email of user
     * @param userPassword password of user
     * @param wineShop     object
     * @return User if founded
     */
    public User executeLogin(String userEmail, String userPassword, WineShop wineShop) {
        PreparedStatement preparedStatement = null;
        User user = null;
        String SELECT_STATEMENT = "SELECT * FROM REL_USER_WINESHOP_EXTENDED u WHERE u.EMAIL = ? AND u.PASSWORD = ? AND u.WINESHOP_ID = ? LIMIT 1";
        try {
            preparedStatement = conn.prepareStatement(SELECT_STATEMENT);

            preparedStatement.setString(1, userEmail);
            preparedStatement.setString(2, userPassword);
            preparedStatement.setInt(3, wineShop.getId());


            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String USER_TYPE = rs.getString("TYPE");
                user = UserFactory.getUser(Type.valueOf(USER_TYPE));
                Integer USER_ID = rs.getInt("USER_ID");
                String USER_NAME = rs.getString("NAME");
                String USER_SURNAME = rs.getString("SURNAME");
                String USER_EMAIL = rs.getString("EMAIL");
                String USER_PASSWORD = rs.getString("PASSWORD");
                if (USER_ID != null)
                    user.setId(USER_ID)
                            .setName(USER_NAME)
                            .setSurname(USER_SURNAME)
                            .setEmail(USER_EMAIL)
                            .setPassword(USER_PASSWORD);

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
        return user;
    }


}
