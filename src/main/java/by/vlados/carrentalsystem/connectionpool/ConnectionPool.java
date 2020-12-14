package by.vlados.carrentalsystem.connectionpool;

import by.vlados.carrentalsystem.exception.DAOException;
import by.vlados.carrentalsystem.util.ConfigurationManager;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.log4j.Logger;

/**
 *
 * @author vlados
 * 
 * Connection pool for accessing Database
 */
public final class ConnectionPool {

    private static final Logger LOG = Logger.getLogger(ConnectionPool.class);

    /**
     * fields for describing connection and pool
     */
    public static ConnectionPool INSTANCE;
    private static ReentrantLock lock;
    private BlockingQueue<Connection> connections;
    private final static String DATABASE_URL = ConfigurationManager.getProperty("db.url");
    private final static String DATABASE_LOGIN = ConfigurationManager.getProperty("db.user");
    private final static String DATABASE_PASSWORD = ConfigurationManager.getProperty("db.password");
    private final static int CONNECTIONS_QUANTITY = Integer.parseInt(ConfigurationManager.getProperty("db.maxpool"));
    private final static String DATABASE_DRIVER = ConfigurationManager.getProperty("db.driver");
    private static boolean flag = true;

    private ConnectionPool() {
        LOG.debug("ConnectionPool()");
        Connection connection;
        if (flag) {
            try {
                flag = false;
                connections = new ArrayBlockingQueue<>(CONNECTIONS_QUANTITY);
                Properties p = new Properties();
                p.setProperty("user", DATABASE_LOGIN);
                p.setProperty("password", DATABASE_PASSWORD);
                p.setProperty("useUnicode", "true");
                p.setProperty("characterEncoding", "utf-8");
                Class.forName(DATABASE_DRIVER).newInstance();

                for (int i = 0; i < CONNECTIONS_QUANTITY; i++) {
                    connection = DriverManager.getConnection(DATABASE_URL, p);
                    if (connection != null) {
                        connections.put(connection);
                    }
                }
            } catch (ClassNotFoundException | SQLException ex) {
                LOG.fatal("Couldn't establish the connection with the db while creating the  ConnectionPool. " + ex);
            } catch (InstantiationException | IllegalAccessException | InterruptedException ex) {
                LOG.fatal("Connection pool exception:" + ex);
            }
        }
    }

    /**
     *
     * @return new Instanse of Connection Pool (if it wasn't alreasy initialized)
     */
    public static ConnectionPool getInstance() {
        LOG.debug("ConnectionPool.getInstance()");
        lock = new ReentrantLock();
        if (INSTANCE == null) {
            lock.lock();
            try { 
                if (INSTANCE == null) {
                    INSTANCE = new ConnectionPool();
                }
            } finally {
                lock.unlock();
            }
        }
        return INSTANCE;
    }

    /**
     *
     * @return connection from the pool
     * @throws DAOException
     */
    public Connection getConnection() throws DAOException {
        LOG.debug("ConnectionPool.getConnection()");
        Connection connection = null;
        try {
            connection = connections.take();
            if (connection == null) {
                throw new DAOException("There are no more available connections to the database");
            }
        } catch (InterruptedException ex) {
            LOG.fatal("Couldn't establish connection to the db. " + ex);
        }
        return connection;
    }

    /**
     *
     * @param connection
     * returning connection to the database
     */
    public void returnConnection(Connection connection) {
        try {
            if (connection != null) {
                connections.put(connection);
            }
        } catch (InterruptedException ex) {
            LOG.error("Exception while returning the connection. " + ex);
        }
    }

    /**
     * releasing all connections and closing the pool. Is called from ServletContextListener
     */
    public static void releaseConnectionPool() {
        if (INSTANCE != null) {
            lock.lock();
            try {
                if (INSTANCE != null) {
                    INSTANCE.connections.stream().filter((connection) -> (connection != null)).forEach((connection) -> {
                        try {
                            connection.close();
                        } catch (SQLException e) {
                            LOG.error("Can't close connection (" + connection + "): ", e);
                        }
                    });
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
