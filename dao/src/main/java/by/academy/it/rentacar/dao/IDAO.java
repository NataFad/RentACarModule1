package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.exceptions.DAOException;

import java.io.Serializable;
import java.util.List;

/**
 * Interface IDAO
 * initializes methods add(), update(), delete(), getAll().
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public interface IDAO<T> {

    void saveOrUpdate(T t) throws DAOException;

    T get(Serializable id) throws DAOException;

    T load(Serializable id) throws DAOException;

    void delete(T t) throws DAOException;

    List<T> getAll() throws DAOException;

    long count() throws DAOException;
}
