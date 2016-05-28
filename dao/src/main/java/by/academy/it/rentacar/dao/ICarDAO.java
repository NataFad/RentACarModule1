package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.viewobject.CarViewObject;
import org.hibernate.HibernateException;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

/**
 * Interface ICarDAO
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 */
public interface ICarDAO extends IDAO<Car> {

    /**
     * Method sqlQueryStringByFilter()
     * returns string of sql-query based on the rental dates and defined filters
     *
     * @param fromDate
     * @param byDate
     * @param filterValues
     * @return String
     */
    String sqlQueryStringByFilter(Date fromDate, Date byDate, HashMap<String, String> filterValues);

    /**
     * Method searchCar()
     * performs a search based on the rental dates and defined filters
     *
     * @param fromDate
     * @param byDate
     * @param filterValues
     * @return list
     * @throws HibernateException
     */
    List<CarViewObject> searchCar(Date fromDate, Date byDate, HashMap<String, String> filterValues);

    /**
     * Method countCarByFilter()
     * performs a search based on the rental dates and defined filters
     *
     * @param fromDate
     * @param byDate
     * @param filterValues
     * @return
     * @throws SQLException
     */
    BigInteger countCarByFilter(Date fromDate, Date byDate, HashMap<String, String> filterValues);


}

