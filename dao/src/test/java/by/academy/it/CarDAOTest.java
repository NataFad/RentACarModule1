package by.academy.it;

import by.academy.it.rentacar.dao.CarDAO;
import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.viewobject.CarViewObject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nata on 14.05.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 */
@Ignore
public class CarDAOTest {

    private static CarDAO carDAO;
    @Test
    public void searchCarTest() throws Exception {
        Date dateForTest  =  Date.valueOf("2016-01-01");
        // the list of the filter
        HashMap<String, String> filterValues = new HashMap<String, String>();
        filterValues.put("transmission", "auto");
        filterValues.put("fuelId", "1");
        filterValues.put("typeId", "1");
        filterValues.put("ratingId", "1");
        filterValues.put("orderBy", "F.name ASC, MAndM.mark ASC");
        filterValues.put("page", "1");
        filterValues.put("recordsPerPage", "10");

        List<CarViewObject> list = carDAO.searchCar(dateForTest, dateForTest, filterValues);
        BigInteger countCar = carDAO.countCarByFilter(dateForTest, dateForTest, filterValues);

        Assert.assertNotNull(list);
        Assert.assertNotNull(countCar);
        Assert.assertEquals(BigInteger.valueOf(list.size()), countCar);
    }

    @Test
    public void getAllCarsTest() throws Exception{
        List<Car> list = (ArrayList<Car>) carDAO.getAll();
        long count = carDAO.count();

        Assert.assertNotNull(list);
        Assert.assertNotNull(count);
        Assert.assertEquals(list.size(), count);
    }

    @AfterClass
    public static void tearDown() throws Exception {
       // HibernateUtil.getInstance().closeSession();
    }
}