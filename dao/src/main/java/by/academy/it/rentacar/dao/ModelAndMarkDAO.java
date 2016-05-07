/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.ModelAndMark;
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

/**
 * Class ModelAndMarkDAO
 * 
 * Class ModelAndMarkDAO creates object ModelAndMarkDAO and executes queries the
 * table ModelsAndMarks.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class ModelAndMarkDAO extends DAO {

	private volatile static ModelAndMarkDAO instance;

	private final String COLUMN_NAME_ID = "id";
	private final String COLUMN_NAME_MARK = "mark";
	private final String COLUMN_NAME_MODEL = "model";

	private ModelAndMarkDAO() {
		super();
	}

	public static ModelAndMarkDAO getInstance() {
		if (instance == null) {
			synchronized (ModelAndMarkDAO.class) {
				if (instance == null) {
					instance = new ModelAndMarkDAO();
				}
			}
		}
		return instance;
	}

	/**
	 * Method getById() searches object model and mark by id
	 *
	 * Implements #GET_MODEL_BY_ID
	 */
	public ModelAndMark getById(int id){
		ModelAndMark model = null;
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_MODEL_BY_ID)){
			ps.setInt(1, id);
			try (ResultSet	result = ps.executeQuery()) {
				if (result.next()) {
					model = new ModelAndMark();
					model.setId(result.getInt(COLUMN_NAME_ID));
					model.setMark(result.getString(COLUMN_NAME_MARK));
					model.setModel(result.getString(COLUMN_NAME_MODEL));
				}
			} catch (SQLException e){
				Logger.getLogger(ModelAndMarkDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(ModelAndMarkDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		return model;
	}

	/**
	 * Method add() writes object model and mark into the table
	 * Implements #ADD_MODEL
	 *
	 * @param o
	 */
	public void add(Object o) {
		ModelAndMark model = (ModelAndMark) o;
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
				 PreparedStatement ps = connection.prepareStatement(ISqlQuery.ADD_MODEL)){
			ps.setString(1, model.getMark());
			ps.setString(2, model.getModel());
			ps.executeUpdate();
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(FuelDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Method getAll() gets data about all models and marks
	 *
   * Implements #GET_ALL_MODELS
   */
  public ArrayList<ModelAndMark> getAll() {
    ArrayList<ModelAndMark> list = new ArrayList<ModelAndMark>();
    try (Connection	connection = DBConnectionPool.getInstance().getConnection();
			PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_ALL_MODELS);
			ResultSet result = ps.executeQuery()){
   		  while (result.next()) {
       		ModelAndMark model = new ModelAndMark();
         	model.setId(result.getInt(COLUMN_NAME_ID));
    	   	model.setMark(result.getString(COLUMN_NAME_MARK));
			  	model.setModel(result.getString(COLUMN_NAME_MODEL));
          list.add(model);
        }
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(ModelAndMarkDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		return list;
  }

	public void update(Object o){
	}

	/**
	 * Method delete() deletes object model and mark from the table
	 * Implements #DELETE_MODEL
	 *
	 * @param o
	 */
	public void delete(Object o) {
		ModelAndMark model = (ModelAndMark) o;
		try (Connection	connection = DBConnectionPool.getInstance().getConnection();
				 PreparedStatement ps = connection.prepareStatement(ISqlQuery.DELETE_MODEL)){
			ps.setInt(1, model.getId());
			ps.executeUpdate();
		} catch (SQLException | IOException| PropertyVetoException e) {
			Logger.getLogger(CarDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
	}
}
