package it.unipr.ingegneria.db.persistance.relations;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.entities.Order;
import it.unipr.ingegneria.entities.Wine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RelOrderWine {

    private Connection conn;
    private static RelOrderWine INSTANCE = null;

    public RelOrderWine() {
        conn = DBContext.getConnection();
    }

    public synchronized static RelOrderWine getInstance() {
        if (INSTANCE == null)
            INSTANCE = new RelOrderWine();
        return INSTANCE;
    }


    public void addAll(List<Wine> wines, Order order) {
        try {
            String SQL_INSERT =
                    "INSERT INTO REL_ORDER_WINE (WINE_ID, ORDER_ID) VALUES (?, ?)";
            PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
            for (Wine wine : wines) {
                preparedStatement.setInt(1, wine.getId());
                preparedStatement.setInt(2, order.getId());
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
