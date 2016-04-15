package by.academy.it.rentacar.dao;

import java.util.ArrayList;

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

    void add(T t);

    void update(T t);

    void delete(T t);

    ArrayList<T> getAll();
}
