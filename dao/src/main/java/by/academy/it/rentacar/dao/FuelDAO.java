package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.beans.Fuel;
import by.academy.it.rentacar.connectionpool.DBConnectionPool;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**Class FuelDAO
 * 
 * Class FuelDAO creates object Fuel and executes queries the table Fuels.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class FuelDAO extends DAO {

    private volatile static FuelDAO instance;
    
    private final String COLUMN_NAME_ID = "id";
    private final String COLUMN_NAME_NAME = "name";
  
    private FuelDAO() {
        super();
    }

    public static FuelDAO getInstance() {
        if (instance == null) {
			synchronized (FuelDAO.class) {
				if (instance == null) {
					instance = new FuelDAO();
				}
			}
		}
        return instance;
    }
     
    /**
     * Method add() writes data of fuel in the table
     *
     * Implements #SQL_ADD_FUEL
     */
	public void add(Object o) throws SQLException {
        Fuel fuel = (Fuel) o;
        String query = sqlManager.getProperty(sqlManager.SQL_ADD_FUEL);
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, fuel.getName());
            ps.executeUpdate();
        } catch (IOException | PropertyVetoException e) {
            Logger.getLogger(FuelDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
            e.printStackTrace();
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Method update() updates data of fuel in the table
     *
     * Implements #SQL_UPDATE_FUEL
     */
    public void update(Object o) throws SQLException {
        Fuel fuel = (Fuel) o;
        String query = sqlManager.getProperty(sqlManager.SQL_UPDATE_FUEL);
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, fuel.getName());
            ps.setInt(2, fuel.getId());
            ps.executeUpdate();
        } catch (IOException | PropertyVetoException e) {
            Logger.getLogger(FuelDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
            e.printStackTrace();
        } finally {
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Method getListFuelsFromResult()
     * transforms result of sql-query in object fuel and adds in list
     *
     * @param result
     * @return ArrayList<Fuel>
     * @throws SQLException
     */
    private ArrayList<Fuel> getListFuelsFromResult(ResultSet result) throws SQLException {
        ArrayList<Fuel> list = new ArrayList<Fuel>();

        while (result.next()) {
            Fuel fuel = new Fuel();

            fuel.setId(result.getInt(COLUMN_NAME_ID));
            fuel.setName(result.getString(COLUMN_NAME_NAME));

            list.add(fuel);
        }
        return list;
    }

    /**
     * Method getAll() gets data about all fuels
     *
     * Implements #SQL_GET_ALL_FUELS
     */
   public ArrayList<Fuel> getAll() throws SQLException {
       String query = sqlManager.getProperty(sqlManager.SQL_GET_ALL_FUELS);
       Connection connection = null;
       PreparedStatement ps = null;
       ResultSet result = null;
       ArrayList<Fuel> list = new ArrayList<Fuel>();
       try {
            connection = DBConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            list = getListFuelsFromResult(result);
        } catch (IOException | PropertyVetoException e) {
            Logger.getLogger(FuelDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
            e.printStackTrace();
        } finally {
            if (result != null) try {
                result.close();
            } catch (SQLException e){
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
        }
        return list;
    }
    
    /**
     * Method getById() searches object fuel by id
     *
     * Implements #SQL_GET_FUELS_BY_ID
     */
	public Fuel getById(int id) throws SQLException {
        Fuel fuel = null;
        String query = sqlManager.getProperty(sqlManager.SQL_GET_FUELS_BY_ID);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            result = ps.executeQuery();
            if (result.next()) {
        	    fuel = new Fuel();
            	fuel.setId(result.getInt(COLUMN_NAME_ID));
            	fuel.setName(result.getString(COLUMN_NAME_NAME));
            }
        } catch (IOException | PropertyVetoException e) {
            Logger.getLogger(FuelDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
            e.printStackTrace();
        } finally {
            if (result != null) try {
                result.close();
            } catch (SQLException e){
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
        }
        return fuel;
    }
    
    /**
     * Method searchByName() searches all fuels by name
     *
     * Implements #SQL_SEARCH_FUEL
     */
	public ArrayList<Fuel> searchByName(String q) throws SQLException{
        q = q.trim();
        String query = sqlManager.getProperty(sqlManager.SQL_SEARCH_FUEL);
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        ArrayList<Fuel> list = new ArrayList<Fuel>();
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, '%'+ q + '%');
            result = ps.executeQuery();
            list = getListFuelsFromResult(result);
        } catch (IOException | PropertyVetoException e) {
            Logger.getLogger(FuelDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
            e.printStackTrace();
        } finally {
            if (result != null) try {
                result.close();
            } catch (SQLException e){
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
        }
        return list;
    }
    
    /**
     * Method count() gets count of entries in the table
     *
     * Implements #SQL_COUNT_FUELS
     */
   public int count() throws SQLException {
        String query = sqlManager.getProperty(sqlManager.SQL_COUNT_FUELS);
        int count = -1;
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet result = null;
        try {
            connection = DBConnectionPool.getInstance().getConnection();
            ps = connection.prepareStatement(query);
            result = ps.executeQuery();
            if(result.next()) {
                count = result.getInt("COUNT(*)");
            }
        } catch (IOException | PropertyVetoException e) {
            Logger.getLogger(FuelDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
            e.printStackTrace();
        } finally {
            if (result != null) try {
                result.close();
            } catch (SQLException e){
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
            if (ps != null) try {
                ps.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
            if (connection != null) try {
                connection.close();
            } catch (SQLException e) {
                Logger.getLogger(FuelDAO.class).error(e.getMessage());
                e.printStackTrace();
            }
        }
        return count;
   }

    public void delete(Object o) throws SQLException {
    }
}
