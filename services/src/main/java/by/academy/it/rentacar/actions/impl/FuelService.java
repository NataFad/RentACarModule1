package by.academy.it.rentacar.actions.impl;


import by.academy.it.rentacar.actions.IFuelService;
import by.academy.it.rentacar.dao.IFuelDAO;
import by.academy.it.rentacar.entity.Fuel;
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
@Service("fuelService")
@Transactional(readOnly = true)
public class FuelService implements IFuelService {

    @Autowired
    private IFuelDAO fuelDAO;

    /**
     * Method getListFuel() gets list of fuels
     *
     * @return ArrayList<Fuel>
     */
    public ArrayList<Fuel> getListFuel() {
        // List of fuels
        ArrayList<Fuel> fuelList = (ArrayList<Fuel>) fuelDAO.getAll();
        return fuelList;
    }

    /**
     * Method getFuelById() gets fuel by id
     *
     * @param fuelId
     * @return fuel
     */
    public Fuel getFuelById(int fuelId) {
        Fuel fuel = fuelDAO.getByKey("id", fuelId);
        return fuel;
    }
}
