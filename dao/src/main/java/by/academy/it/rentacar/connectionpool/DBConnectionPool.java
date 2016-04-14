package by.academy.it.rentacar.connectionpool;

import by.academy.it.rentacar.managers.DBSqlManager;
import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

  private static DBConnectionPool connectionPool;
  private ComboPooledDataSource cpds;

  private DBConnectionPool() throws IOException, SQLException, PropertyVetoException {
    DBSqlManager dbSqlManager = DBSqlManager.getInstance();
    cpds = new ComboPooledDataSource();
    cpds.setDriverClass(dbSqlManager.getProperty("DB_CONNECTION_DRIVERNAME"));
    cpds.setJdbcUrl(dbSqlManager.getProperty("DB_CONNECTION_URL"));
    cpds.setUser(dbSqlManager.getProperty("DB_CONNECTION_USER"));
    cpds.setPassword(dbSqlManager.getProperty("DB_CONNECTION_PASSWORD"));
    // the settings below are optional -- c3p0 can work with defaults
    cpds.setMinPoolSize(5);
    cpds.setAcquireIncrement(5);
    cpds.setMaxPoolSize(20);
    cpds.setMaxStatements(180);

  }

  public static DBConnectionPool getInstance() throws IOException, SQLException, PropertyVetoException {
    if (connectionPool == null) {
      connectionPool = new DBConnectionPool();
      return connectionPool;
    } else {
      return connectionPool;
    }
  }

  public Connection getConnection() throws SQLException {
    return this.cpds.getConnection();
  }
}