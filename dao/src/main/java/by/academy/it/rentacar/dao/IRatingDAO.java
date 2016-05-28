package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Rating;

/**
 * Interface IRatingDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 *
 */
public interface IRatingDAO {

    /**
     * Method getById() searches object rating by id
     *
     */
    Rating getById(int id);
}
