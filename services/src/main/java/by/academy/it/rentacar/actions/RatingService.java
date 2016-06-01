package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.dao.IRatingDAO;
import by.academy.it.rentacar.entity.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Class RatingService induces RatingDAO
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@Service("ratingService")
@Transactional(readOnly = true)
public class RatingService implements IRatingService {

    @Autowired
    private IRatingDAO ratingDAO;

    /**
     * Method getListRating() gets list of ratings
     *
     * @return ArrayList<Rating>
     */
    public ArrayList<Rating> getListRating() {
        // List of ratings
        ArrayList<Rating> ratingList = (ArrayList<Rating>) ratingDAO.getAll();
        return ratingList;
    }

    /**
     * Method getRatingById() gets fuel by id
     *
     * @param ratingId
     * @return rating
     */
    public Rating getRatingById(int ratingId) {
        Rating rating = ratingDAO.getByKey("id", ratingId);
        return rating;
    }
}
