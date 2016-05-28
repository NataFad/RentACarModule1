package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Fuel;

import java.util.List;

/**
 * Interface IFuelDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 */
public interface IFuelDAO {

    /**
     * Method searchByName() searches all fuels by name
     *
     */
    List<Fuel> searchByName(String nameSearch);

    /**
     * Method getById() searches object fuel by id
     *
     */
    Fuel getById(int id);
}
