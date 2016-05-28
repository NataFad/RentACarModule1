package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.dao.RatingDAO;
import by.academy.it.rentacar.entity.Rating;

import java.util.ArrayList;

/**
 * Class RatingService induces RatingDAO
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 *
 */
public class RatingService implements IRatingService{

    private volatile static RatingService instance;
    private RatingDAO ratingDAO;

    private RatingService(){}

    public static RatingService getInstance() {
        if (instance == null) {
            synchronized (RatingService.class) {
                if (instance == null) {
                    instance = new RatingService();
                }
            }
        }
        return instance;
    }

    /**
     * Method getListRating() gets list of ratings
     *
     * @return ArrayList<Rating>
     */
    public ArrayList<Rating> getListRating(){
        // List of ratings
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        //try {
            ratingList = (ArrayList<Rating>) ratingDAO.getAll();
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
        return ratingList;
    }

    /**
     * Method getRatingById() gets fuel by id
     *
     * @param ratingId
     * @return rating
     */
    public Rating getRatingById(int ratingId){
        Rating rating = null;
        //try {
            rating = ratingDAO.getByKey("id", ratingId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return rating;
    }
}
