package by.academy.it.rentacar.dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Nata on 13.04.2016.
 */
public interface IDAO<T> {

    void add(T t) throws SQLException;

    void update(T t) throws SQLException ;

    void delete(T t) throws SQLException ;

    ArrayList<T> getAll() throws SQLException;

    int count() throws SQLException;
}
