package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.dao.FuelDAO;
import by.academy.it.rentacar.entity.Fuel;
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
public class FuelService {

    private volatile static FuelService instance;

    private FuelService(){}

    public static FuelService getInstance() {
        if (instance == null) {
            synchronized (FuelService.class) {
                if (instance == null) {
                    instance = new FuelService();
                }
            }
        }
        return instance;
    }

    /**
     * Method getListFuel() gets list of ratings
     *
     * @return ArrayList<Fuel>
     */
    public ArrayList<Fuel> getListFuel(){
        // List of fuels
        ArrayList<Fuel> fuelList = new ArrayList<Fuel>();
        try {
            fuelList = (ArrayList<Fuel>) FuelDAO.getInstance().getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return fuelList;
    }
}
