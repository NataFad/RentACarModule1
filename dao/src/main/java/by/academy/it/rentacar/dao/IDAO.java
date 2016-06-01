package by.academy.it.rentacar.dao;

import java.io.Serializable;
import java.util.List;

/**
 * Interface IDAO
 * initializes methods add(), update(), delete(), getAll().
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 *
 */
public interface IDAO<T> {

    /**
     * Method saveOrUpdate() saves or updates object T from the table
     *
     * @param entity
     */
    void saveOrUpdate(T entity);

    /**
     * Method get() gets the object T by id from the table
     *
     * @param id
     * @return entity
     */
    T get(Serializable id);

    /**
     * Method load() gets the object T by id from the table
     *
     * @param id
     * @return entity
     */
    T load(Serializable id);

    /**
     * Method getByKey() searches by the value of the key
     */
    T getByKey(String key, Serializable value);

    /**
     * Method delete() deletes object T from the table
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * Method getAll() gets all objects T from the table
     *
     * @return results
     */
    List<T> getAll();

    /**
     * Method count() gets count of entries in the table
     * <p>
     */
    long count();
}
