package by.academy.it.rentacar.connectionpool;

import by.academy.it.rentacar.managers.DBSqlManager;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Deque;
import java.util.LinkedList;

/**Class DBConnectionPool
 * is connecting to the database and creates a pool of connections
 * The options has read from file-properties
 *
 * @author  Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class DBConnectionPool {

    private volatile static DBConnectionPool instance;
    private String URL;
    private String user;
    private String password;
    private String driverName;

    private Deque<Connection> deque;

	
    private DBConnectionPool() {

        try {
            URL = DBSqlManager.getInstance().getProperty("DB_CONNECTION_URL");
            user = DBSqlManager.getInstance().getProperty("DB_CONNECTION_USER");
            password = DBSqlManager.getInstance().getProperty("DB_CONNECTION_PASSWORD");
            driverName = DBSqlManager.getInstance().getProperty("DB_CONNECTION_DRIVERNAME");

            Class.forName(driverName);
            deque = new LinkedList<Connection>();
            
       } catch (ClassNotFoundException e) {
            Logger.getLogger(this.getClass()).error(e.toString());
        }
    }

    /**
     * Method getInstance()
     * creates an object
     *
     * @return
     */
    public static DBConnectionPool getInstance() {
    	if (instance == null) {
			synchronized (DBConnectionPool.class) {
				if (instance == null) {
					instance = new DBConnectionPool();
				}
			}
		}
		return instance;
    }

    /**
     * Method getConnection()
     * takes from the queue or generates a new connection
     *
     * @return
     * @throws SQLException
     */
    public synchronized Connection getConnection() throws SQLException {
        if (!deque.isEmpty()) {
            while (!deque.isEmpty()) {
                Connection connection = deque.poll();
                if (connection.isValid(500)) {
                    return connection;
                }
                return DriverManager.getConnection(URL, user, password);
            }
        }
        return DriverManager.getConnection(URL, user, password);
    }

    /**
     * Method freeConnection()
     * the released connection has added in the queue
     *
     * @param connection
     */
    public synchronized void freeConnection(Connection connection) {
        try {
            if(!connection.isClosed()) {
                deque.add(connection);
            }
        } catch (SQLException e) {
            return;
        }
    }
}
