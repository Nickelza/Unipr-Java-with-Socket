package it.unipr.ingegneria;

import it.unipr.ingegneria.db.DBContext;
import it.unipr.ingegneria.db.persistance.WarehouseDAO;
import it.unipr.ingegneria.db.persistance.WineShopDAO;
import it.unipr.ingegneria.db.persistance.relations.RelWineshopWarehouse;
import it.unipr.ingegneria.impl.Warehouse;
import it.unipr.ingegneria.impl.WineShop;

import java.io.IOException;
import java.net.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * The {@code MainServer} define the main server that open connection  .
 *
 * @author Ruslan Vasyunin, Francesca Rossi, Everton Ejike
 */
public class MainServer {

    private static final int COREPOOL = 5;
    private static final int MAXPOOL = 100;
    private static final long IDLETIME = 5000;
    private static final int SPORT = 4445;

    private static final String ADDRESS = "230.0.0.1";
    private static final int DPORT = 4446;

    private WineShop shop;
    private MulticastSocket multicastSocket;
    private ServerSocket socket;


    private ThreadPoolExecutor pool;

    /**
     * Class constructor.
     *
     * @throws IOException  if the creation of the server socket fails.
     * @throws SQLException if the execute clear all tables fails.
     **/
    public MainServer() throws IOException, SQLException {
        // Clear all tables by previous running
        clearAllTables();

        InetAddress inetA = InetAddress.getByName(ADDRESS);
        InetSocketAddress group = new InetSocketAddress(inetA, DPORT);
        NetworkInterface netI = NetworkInterface.getByInetAddress(inetA);
        this.multicastSocket = new MulticastSocket(DPORT);
        multicastSocket.joinGroup(group, netI);

        // Define the default WineShop that will be used in the lifecycle application

        Warehouse warehouse = new Warehouse("WAREHOUSE-0");
        WarehouseDAO.getInstance().add(warehouse);

        this.shop = new WineShop("Enoteca Galvani", warehouse);
        WineShopDAO.getInstance().add(this.shop);

        RelWineshopWarehouse.getInstance().add(shop, warehouse);

        this.socket = new ServerSocket(SPORT);

    }

    /**
     * Runs the server code.
     **/
    public void run() {
        this.pool = new ThreadPoolExecutor(COREPOOL, MAXPOOL, IDLETIME,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

        while (true) {
            try {
                Socket s = this.socket.accept();
                this.pool.execute(new ServerThread(this, s, shop));
            } catch (Exception e) {
                break;
            }
        }

        this.pool.shutdown();
    }

    /**
     * Gets the server pool.
     *
     * @return the thread pool.
     **/
    public ThreadPoolExecutor getPool() {
        return this.pool;
    }

    /**
     * Closes the server execution.
     **/
    public void close() {
        try {
            this.socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Clear database connection
     */
    private static void clearAllTables() throws SQLException {

        Connection conn = DBContext.getConnection();
        Statement statement = conn.createStatement();
        statement.addBatch("DELETE FROM ORDER_ITEM");
        statement.addBatch("DELETE FROM REL_ORDER_USER");
        statement.addBatch("DELETE FROM REL_ORDER_WINE");
        statement.addBatch("DELETE FROM REL_USER_WINESHOP");
        statement.addBatch("DELETE FROM REL_USER_WINESHOP");
        statement.addBatch("DELETE FROM REL_WINESHOP_WAREHOUSE");
        statement.addBatch("DELETE FROM REL_WINE_VINEYARD");
        statement.addBatch("DELETE FROM REL_WINE_WAREHOUSE");
        statement.addBatch("DELETE FROM USER");
        statement.addBatch("DELETE FROM VINEYARD");
        statement.addBatch("DELETE FROM WINE");
        statement.addBatch("DELETE FROM WAREHOUSE");
        statement.addBatch("DELETE FROM WINESHOP");
        int[] rs = statement.executeBatch();
    }
}
