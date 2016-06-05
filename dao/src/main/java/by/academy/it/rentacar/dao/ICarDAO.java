package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.viewobject.CarVO;
import by.academy.it.rentacar.viewobject.FilterVO;
import org.hibernate.HibernateException;

import java.sql.SQLException;
import java.util.List;

/**
 * Interface ICarDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 */
public interface ICarDAO extends IDAO<Car> {

    /**
     * Method sqlQueryStringByFilter()
     * returns string of sql-query based on the rental dates and defined filters
     *
    * @param filterVO
     * @return String
     */
    String sqlQueryStringByFilter(FilterVO filterVO);

    /**
     * Method searchCar()
     * performs a search based on the rental dates and defined filters
     *
     * @param filterVO
     * @return list
     * @throws HibernateException
     */
    List<CarVO> searchCar(FilterVO filterVO, int page);

    /**
     * Method countCarByFilter()
     * performs a search based on the rental dates and defined filters
     *
     * @param filterVO
     * @return
     * @throws SQLException
     */
    int countCarByFilter(FilterVO filterVO);


}

