package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.ModelAndMark;

/**
 * Interface IModelAndMarkDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 *
 */
public interface IModelAndMarkDAO extends IDAO<ModelAndMark> {

    /**
     * Method getById() searches object model and mark by id
     *
     */
    ModelAndMark getById(int id);
}
