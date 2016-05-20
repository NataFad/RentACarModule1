/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Rating;
import org.apache.log4j.Logger;

/**
 * Class RatingDAO
 * 
 * Class RatingDAO creates object Rating and executes queries the table Ratings.
 * 
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 * 
 */
public class RatingDAO extends DAO<Rating> {

	private volatile static RatingDAO instance;
	private static Logger log = Logger.getLogger(RatingDAO.class);

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
	 */
	public Rating getById(int id){
		return getByKey("id", id);
	}
}
