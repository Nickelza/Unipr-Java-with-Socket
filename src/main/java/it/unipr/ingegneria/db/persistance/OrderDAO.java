package it.unipr.ingegneria.db.persistance;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.DTO.OrderDTO;
import it.unipr.ingegneria.db.IOperations;
import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Vineyard;
import it.unipr.ingegneria.entities.Wine;
import org.antlr.stringtemplate.StringTemplate;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrderDAO implements IOperations<Order> {

    private Connection conn;
    private static OrderDAO INSTANCE = null;
    private static final Logger LOGGER = Logger.getLogger(OrderDAO.class);

    public OrderDAO() {
        conn = DBContext.getConnection();
    }

    public synchronized static OrderDAO getInstance() {
        if (INSTANCE == null)
            INSTANCE = new OrderDAO();
        return INSTANCE;
    }

    @Override
    public void add(Order order) {
        PreparedStatement statement = null;
        Integer generatedId = null;
        try {

            StringTemplate INSERT_STATMENT =
                    new StringTemplate("INSERT INTO ORDER_ITEM (DATE, DELIVERED) VALUES ('$DATE_VALUE$', '$IS_DELIVERED$')");

            Date date = order.getDate();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
            String strDate = dateFormat.format(date);
            String orderIsDelivered = order.isDelivered() ? "Y" : "N";

            INSERT_STATMENT.setAttribute("DATE_VALUE", strDate);
            INSERT_STATMENT.setAttribute("IS_DELIVERED", orderIsDelivered);

            String SQL_INSERT = INSERT_STATMENT.toString();

            statement = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
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
                statement.close();
            } catch (SQLException e) {
                LOGGER.error(e);
            }
        }
    }


    public List<OrderDTO> findAll() {
        String SQL_FIND_ALL =
                "SELECT USER_ID, WINE_NAME, ORDER_ID, ORDER_DATE, ORDER_DELIVERED, COUNT(*) AS QTY FROM REL_ORDER_USER_WINE_EXTENDED GROUP BY USER_ID, WINE_NAME, ORDER_ID";

        return buildOrderDTO(SQL_FIND_ALL);
    }

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
