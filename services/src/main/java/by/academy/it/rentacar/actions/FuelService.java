package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.dao.FuelDAO;
import by.academy.it.rentacar.entity.Fuel;

import java.util.ArrayList;

/**
 * Class RatingService induces RatingDAO
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 *
 */
public class FuelService implements IFuelService{

    private volatile static FuelService instance;
    private FuelDAO fuelDAO;

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
     * Method getListFuel() gets list of fuels
     *
     * @return ArrayList<Fuel>
     */
    public ArrayList<Fuel> getListFuel(){
        // List of fuels
        ArrayList<Fuel> fuelList = new ArrayList<Fuel>();
        //try {
            fuelList = (ArrayList<Fuel>) fuelDAO.getAll();
//        } catch (DAOException e) {
//            e.printStackTrace();
//        }
        return fuelList;
    }

    /**
     * Method getFuelById() gets fuel by id
     *
     * @param fuelId
     * @return fuel
     */
    public Fuel getFuelById(int fuelId){
       Fuel fuel = null;
       // try {
            fuel = fuelDAO.getByKey("id", fuelId);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return fuel;
    }
}
