package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.DTO.OrderDTO;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.entities.Order;
import org.apache.log4j.Logger;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The {@code OrderDAO} handle the persistance and retrival data of Order entity.
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class OrderDAO implements IOperations<Order> {

    private Connection conn;
    private static OrderDAO INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);

    private OrderDAO() {
        conn = DBContext.getConnection();
    }

    /**
     * Return {@code OrderDAO} singleton instance
     */
    public synchronized static OrderDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new OrderDAO();
        return INSTANCE;
    }


    /**
     * Method to add the Order in the database
     *
     * @param order order object
     */
    @Override
    public void add(Order order) {
        PreparedStatement preparedStatement = null;
        Integer generatedId = null;
        try {

            String INSERT_STATMENT = "INSERT INTO ORDER_ITEM (DATE, DELIVERED) VALUES (?, ?)";
            preparedStatement = conn.prepareStatement(INSERT_STATMENT, Statement.RETURN_GENERATED_KEYS);

            Date date = order.getDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            String orderIsDelivered = order.isDelivered() ? "Y" : "N";

            preparedStatement.setString(1, strDate);
            preparedStatement.setString(2, orderIsDelivered);



            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        generatedId = generatedKeys.getInt(1);
                        order.setId(generatedId);
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
     * Method to retrive all Order summary
     *
     * @return List of OrderDTO
     */
    public List<OrderDTO> findAll() {
        String SQL_FIND_ALL =
                "SELECT USER_ID, WINE_NAME, ORDER_ID, ORDER_DATE, ORDER_DELIVERED, COUNT(*) AS QTY FROM REL_ORDER_USER_WINE_EXTENDED GROUP BY USER_ID, WINE_NAME, ORDER_ID";

        return buildOrderDTO(SQL_FIND_ALL);
    }

    /**
     * Method to sending the orders
     */
    public void updateOrders() {
        String SQL_UPDATE_ALL =
                "UPDATE ORDER_ITEM SET DELIVERED = 'Y' WHERE ID IN (SELECT ORDER_ID FROM `REL_ORDER_USER` WHERE USER_ID IN (SELECT USER_ID FROM REL_USER_WINESHOP)) AND DELIVERED = 'N'";
        Statement statement = null;
        try {
            statement = conn.createStatement();
            statement.executeUpdate(SQL_UPDATE_ALL);
        } catch (Exception e) {
            LOGGER.error(e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }

    /**
     * Build the object executing the passed query
     *
     * @param sql the executed query
     * @return List of OrderDTO
     */
    public List<OrderDTO> buildOrderDTO(String sql) {
        List<OrderDTO> items = new ArrayList<>();
        Statement statement = null;
        try {
            statement = conn.createStatement();

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                Integer USER_ID = rs.getInt("USER_ID");
                String WINE_NAME = rs.getString("WINE_NAME");
                Integer ORDER_ID = rs.getInt("ORDER_ID");
                String ORDER_DATE = rs.getString("ORDER_DATE");
                String ORDER_DELIVERED = rs.getString("ORDER_DELIVERED");
                Integer QTY = rs.getInt("QTY");
                OrderDTO orderDTO = new OrderDTO()
                        .setUserId(USER_ID)
                        .setWineName(WINE_NAME)
                        .setOrderId(ORDER_ID)
                        .setOrderDate(ORDER_DATE)
                        .setOrderDelevered(ORDER_DELIVERED.equals("Y") ? Boolean.TRUE : Boolean.FALSE)
                        .setOrderQty(QTY);
                items.add(orderDTO);
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
