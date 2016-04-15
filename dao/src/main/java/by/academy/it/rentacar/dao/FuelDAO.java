package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.beans.Fuel;
import by.academy.it.rentacar.connectionpool.DBConnectionPool;
import by.academy.it.rentacar.constants.ISqlQuery;
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
  * Implements #ADD_FUEL
  */
	public void add(Object o) {
    Fuel fuel = (Fuel) o;
    try (Connection connection = DBConnectionPool.getInstance().getConnection();
      PreparedStatement ps = connection.prepareStatement(ISqlQuery.ADD_FUEL)){
        ps.setString(1, fuel.getName());
        ps.executeUpdate();
    } catch (SQLException | IOException | PropertyVetoException e) {
      Logger.getLogger(FuelDAO.class).error(e.getMessage());
      e.printStackTrace();
    }
  }

  /**
  * Method update() updates data of fuel in the table
  *
  * Implements #UPDATE_FUEL
  */
  public void update(Object o) {
    Fuel fuel = (Fuel) o;
    try (Connection connection = DBConnectionPool.getInstance().getConnection();
      PreparedStatement ps = connection.prepareStatement(ISqlQuery.UPDATE_FUEL)){
        ps.setString(1, fuel.getName());
        ps.setInt(2, fuel.getId());
        ps.executeUpdate();
    } catch (SQLException | IOException | PropertyVetoException e) {
      Logger.getLogger(FuelDAO.class).error(e.getMessage());
      e.printStackTrace();
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
   * Implements #GET_ALL_FUELS
   */
  public ArrayList<Fuel> getAll() {
    ArrayList<Fuel> list = new ArrayList<Fuel>();
    try (Connection connection = DBConnectionPool.getInstance().getConnection();
      PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_ALL_FUELS);
      ResultSet result = ps.executeQuery()){
        list = getListFuelsFromResult(result);
    } catch (SQLException | IOException | PropertyVetoException e) {
      Logger.getLogger(FuelDAO.class).error(e.getMessage());
      e.printStackTrace();
    }
    return list;
  }
    
  /**
   * Method getById() searches object fuel by id
   *
   * Implements #GET_FUEL_BY_ID
   */
	public Fuel getById(int id) {
    Fuel fuel = null;
    try (Connection connection = DBConnectionPool.getInstance().getConnection();
        PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_FUEL_BY_ID)){
      ps.setInt(1, fuel.getId());
      try (ResultSet result = ps.executeQuery()) {
        if (result.next()) {
          fuel = new Fuel();
          fuel.setId(result.getInt(COLUMN_NAME_ID));
          fuel.setName(result.getString(COLUMN_NAME_NAME));
        }
      }catch (SQLException e){
        Logger.getLogger(Fuel.class).error(e.getMessage());
        e.printStackTrace();
      }
    } catch (SQLException | IOException | PropertyVetoException e) {
      Logger.getLogger(FuelDAO.class).error(e.getMessage());
      e.printStackTrace();
    }
    return fuel;
  }
    
    /**
     * Method searchByName() searches all fuels by name
     *
     * Implements #SEARCH_FUEL
     */
	public ArrayList<Fuel> searchByName(String q){
    q = q.trim();
    ArrayList<Fuel> list = new ArrayList<Fuel>();
    try (Connection connection = DBConnectionPool.getInstance().getConnection();
      PreparedStatement ps = connection.prepareStatement(ISqlQuery.SEARCH_FUEL)){
      ps.setString(1, '%'+q+'%');
      try (ResultSet result = ps.executeQuery()){
        list = getListFuelsFromResult(result);
      } catch (SQLException e) {
        Logger.getLogger(FuelDAO.class).error(e.getMessage());
        e.printStackTrace();
      }
    } catch (SQLException | IOException | PropertyVetoException e) {
      Logger.getLogger(FuelDAO.class).error(e.getMessage());
      e.printStackTrace();
    }
    return list;
  }
    
  /**
   * Method count() gets count of entries in the table
   *
   * Implements #COUNT_FUELS
   */
  public int count(){
    int count = -1;
    try (Connection connection = DBConnectionPool.getInstance().getConnection();
      PreparedStatement ps = connection.prepareStatement(ISqlQuery.COUNT_FUELS);
      ResultSet result = ps.executeQuery()){
        if(result.next()) {
          count = result.getInt("COUNT(*)");
        }
    } catch (SQLException | IOException | PropertyVetoException e) {
      Logger.getLogger(FuelDAO.class).error(e.getMessage());
      e.printStackTrace();
    }
    return count;
  }

  /**
   * Method delete() deletes object fuel from the table
   * Implements #DELETE_FUEL
   *
   * @param o
   */
  public void delete(Object o){
    Fuel fuel = (Fuel) o;
    try (Connection	connection = DBConnectionPool.getInstance().getConnection();
         PreparedStatement ps = connection.prepareStatement(ISqlQuery.DELETE_FUEL)){
      ps.setInt(1, fuel.getId());
      ps.executeUpdate();
    } catch (SQLException | IOException | PropertyVetoException e) {
      Logger.getLogger(FuelDAO.class).error(e.getMessage());
      e.printStackTrace();
    }
  }
}
