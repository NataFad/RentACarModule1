package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.enums.Transmission;

/**
 * Iterface IPriceDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 */
public interface IPriceDAO extends IDAO<Price>{

    /**
     * Method getByTransmissionAndFuel()
     * searches object price by transmission and fuel
     */
    Price getByTransmissionAndFuel(Transmission transmission, Fuel fuel);


}
