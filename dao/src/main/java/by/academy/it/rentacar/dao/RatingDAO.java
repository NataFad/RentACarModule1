/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.beans.Rating;
import by.academy.it.rentacar.connectionpool.DBConnectionPool;
import org.apache.log4j.Logger;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Class RatingDAO
 * 
 * Class RatingDAO creates object Rating and executes queries the table Ratings.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class RatingDAO extends DAO {

	private volatile static RatingDAO instance;

	private final String COLUMN_NAME_ID = "id";
	private final String COLUMN_NAME_NAME = "name";
	private final String COLUMN_NAME_RATECOST = "rateCost";

	private RatingDAO() {
		super();
	}

	public static RatingDAO getInstance() {
		if (instance == null) {
			synchronized (RatingDAO.class) {
				if (instance == null) {
					instance = new RatingDAO();
				}
			}
		}
		return instance;
	}

	/**
	 * Method getById() searches object rating by id
	 *
	 * Implements #SQL_GET_RATINGS_BY_ID
	 */
	public Rating getById(int id) throws SQLException {
		Rating rating = null;
		String query = sqlManager.getProperty(sqlManager.SQL_GET_RATINGS_BY_ID);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			ps.setInt(1, id);
			result = ps.executeQuery();
			if (result.next()) {
				rating = new Rating();
				rating.setId(result.getInt(COLUMN_NAME_ID));
				rating.setName(result.getString(COLUMN_NAME_NAME));
				rating.setRateCost(result.getBigDecimal(COLUMN_NAME_RATECOST));
			}
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(RatingDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e){
				Logger.getLogger(RatingDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(RatingDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(RatingDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
		return rating;
	}

	/**
	 * Method getAll() gets data about all ratings
	 *
	 * Implements #SQL_GET_ALL_RATINGS
	 */
	public ArrayList<Rating> getAll() throws SQLException {
		ArrayList<Rating> ratingList = new ArrayList<Rating>();
		String query = sqlManager.getProperty(sqlManager.SQL_GET_ALL_RATINGS);
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet result = null;
		try {
			connection = DBConnectionPool.getInstance().getConnection();
			ps = connection.prepareStatement(query);
			result = ps.executeQuery();
			while (result.next()) {
				Rating rating = new Rating();
        		rating.setId(result.getInt(COLUMN_NAME_ID));
				rating.setName(result.getString(COLUMN_NAME_NAME));
				rating.setRateCost(result.getBigDecimal(COLUMN_NAME_RATECOST));
				ratingList.add(rating);
			}
		} catch (IOException | PropertyVetoException e) {
			Logger.getLogger(RatingDAO.class).error("SQL, IOE or PropertyVetoException occurred during adding student");
			e.printStackTrace();
		} finally {
			if (result != null) try {
				result.close();
			} catch (SQLException e){
				Logger.getLogger(RatingDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (ps != null) try {
				ps.close();
			} catch (SQLException e) {
				Logger.getLogger(RatingDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
			if (connection != null) try {
				connection.close();
			} catch (SQLException e) {
				Logger.getLogger(RatingDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		}
		return ratingList;
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
