package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.entity.Rating;
import by.academy.it.rentacar.dao.RatingDAO;
import by.academy.it.rentacar.exceptions.DAOException;

import java.util.ArrayList;

/**
 * Class RatingService induces RatingDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class RatingService {

    private volatile static RatingService instance;

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
        try {
            ratingList = (ArrayList<Rating>) RatingDAO.getInstance().getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return ratingList;
    }

    /**
     * Method getById calls the method in RatingDAO
     *
     * @param id
     * @return
     */
    public Rating getById(int id){
        Rating rating = null;
        try {
            rating = RatingDAO.getInstance().get(id);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return rating;
    }

}
