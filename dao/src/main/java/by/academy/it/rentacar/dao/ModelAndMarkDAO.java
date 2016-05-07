/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.connectionpool.DBConnectionPool;
import by.academy.it.rentacar.constants.ISqlQuery;
import by.academy.it.rentacar.entity.ModelAndMark;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
public class ModelAndMarkDAO extends DAO<ModelAndMark>{

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
		String hql = "SELECT MAM FROM ModelAndMark as MAM WHERE MAM.id = :modelId";
		Session session = util.getSession();
		Query query = session.createQuery(hql);
		query.setParameter("modelId", id);
		List<ModelAndMark> result = (ArrayList<ModelAndMark>) query.list();
		if (!result.isEmpty()) {
			model = result.get(0);
		}
		return model;
	}
}
