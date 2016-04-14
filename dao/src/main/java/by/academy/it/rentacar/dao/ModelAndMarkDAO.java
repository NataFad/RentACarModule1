/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.beans.ModelAndMark;

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
	 * Implements #SQL_GET_MODELS_BY_ID
	 */
	public ModelAndMark getById(int id) throws SQLException {
		ModelAndMark model = null;
		String query = sqlManager.getProperty(sqlManager.SQL_GET_MODELS_BY_ID);
		Connection connection = poolInstance.getConnection();
		PreparedStatement ps = connection.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet result = ps.executeQuery();

		if (result.next()) {
			model = new ModelAndMark();

			model.setId(result.getInt(COLUMN_NAME_ID));
			model.setMark(result.getString(COLUMN_NAME_MARK));
			model.setModel(result.getString(COLUMN_NAME_MODEL));
		}
		poolInstance.freeConnection(connection);
		return model;
	}
	 
    /**
	 * Method getAll() gets data about all models and marks
	 *
     * Implements #SQL_GET_ALL_MODELS
     */
    public ArrayList<ModelAndMark> getAll() throws SQLException {
        ArrayList<ModelAndMark> list = new ArrayList<ModelAndMark>();
        String query = sqlManager.getProperty(sqlManager.SQL_GET_ALL_MODELS);
        Connection connection = poolInstance.getConnection();
          
        PreparedStatement ps = connection.prepareStatement(query);
        ResultSet result = ps.executeQuery();
        while (result.next()) {
        	ModelAndMark model = new ModelAndMark();
            
        	model.setId(result.getInt(COLUMN_NAME_ID));
        	model.setMark(result.getString(COLUMN_NAME_MARK));
			model.setModel(result.getString(COLUMN_NAME_MODEL));
           
            list.add(model);
        }
        poolInstance.freeConnection(connection);
        return list;
    }

	public void add(Object o) throws SQLException {
	}

	public void update(Object o) throws SQLException {
	}

	public void delete(Object o) throws SQLException {
	}

	public int count() throws SQLException {
		return 0;
	}
}
