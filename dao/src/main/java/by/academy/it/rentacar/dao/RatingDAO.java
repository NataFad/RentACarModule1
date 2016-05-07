/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Rating;
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
	 * Implements #GET_RATING_BY_ID
	 */
	public Rating getById(int id){
		Rating rating = null;
		try (Connection	connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_RATING_BY_ID)){
			ps.setInt(1, id);
		  try (ResultSet result = ps.executeQuery()) {
				if (result.next()) {
					rating = new Rating();
					rating.setId(result.getInt(COLUMN_NAME_ID));
					rating.setName(result.getString(COLUMN_NAME_NAME));
					rating.setRateCost(result.getBigDecimal(COLUMN_NAME_RATECOST));
				}
			} catch (SQLException e){
				Logger.getLogger(RatingDAO.class).error(e.getMessage());
				e.printStackTrace();
			}
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(RatingDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		return rating;
	}

	/**
	 * Method getAll() gets data about all ratings
	 *
	 * Implements #GET_ALL_RATINGS
	 */
	public ArrayList<Rating> getAll(){
		ArrayList<Rating> ratingList = new ArrayList<Rating>();
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.GET_ALL_RATINGS);
				ResultSet result = ps.executeQuery()){
			while (result.next()) {
				Rating rating = new Rating();
				rating.setId(result.getInt(COLUMN_NAME_ID));
				rating.setName(result.getString(COLUMN_NAME_NAME));
				rating.setRateCost(result.getBigDecimal(COLUMN_NAME_RATECOST));
				ratingList.add(rating);
			}
		} catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(RatingDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
		return ratingList;
	}

	/**
	 * Method add() writes object rating into the table
	 * Implements #ADD_RATINGS
	 *
	 * @param o
   */
	public void add(Object o){
		Rating rating = (Rating) o;
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.ADD_RATING)) {
			ps.setString(1, rating.getName());
			ps.setBigDecimal(2, rating.getRateCost());
			ps.executeUpdate();
		}catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(RatingDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
	}

	public void update(Object o) {
	}

	/**
	 * Method delete() deletes object rating from the table
	 * Implements #DELETE_RATINGS
	 *
	 * @param o
   */
	public void delete(Object o) {
		Rating rating = (Rating) o;
		try (Connection connection = DBConnectionPool.getInstance().getConnection();
				PreparedStatement ps = connection.prepareStatement(ISqlQuery.DELETE_RATING)) {
			ps.setInt(1, rating.getId());
			ps.executeUpdate();
		}catch (SQLException | IOException | PropertyVetoException e) {
			Logger.getLogger(RatingDAO.class).error(e.getMessage());
			e.printStackTrace();
		}
	}
}
