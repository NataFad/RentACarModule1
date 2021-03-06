/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Rating;
import org.springframework.stereotype.Repository;

/**
 * Class RatingDAO
 * 
 * Class RatingDAO creates object Rating and executes queries the table Ratings.
 * 
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 * 
 */
@Repository("ratingDAO")
public class RatingDAO extends DAO<Rating> implements IRatingDAO{

	public RatingDAO() {
		super();
	}

	@Override
	public Rating getById(int id){
		return getByKey("id", id);
	}
}
