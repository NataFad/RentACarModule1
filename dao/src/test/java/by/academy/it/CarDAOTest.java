package by.academy.it;

import by.academy.it.rentacar.by.academy.it.rentacar.util.HibernateUtil;
import by.academy.it.rentacar.dao.CarDAO;
import by.academy.it.rentacar.entity.Car;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nata on 14.05.2016.
 */
public class CarDAOTest {

    @Test
    public void searchCarTest() throws Exception {
        Date dateForTest  =  Date.valueOf("2016-01-01");
        // the list of the filter
        HashMap<String, String> filterValues = new HashMap<String, String>();
        filterValues.put("transmission", "auto");
        //filterValues.put("fuelId", "1");
        //filterValues.put("typeId", "1");
        filterValues.put("ratingId", "1");

        List<Car> list = CarDAO.getInstance().searchCar(dateForTest, dateForTest, filterValues);
        BigInteger countCar = CarDAO.getInstance().countCarByFilter(dateForTest, dateForTest, filterValues);

        Assert.assertNotNull(list);
        Assert.assertNotNull(countCar);
        Assert.assertEquals(BigInteger.valueOf(list.size()), countCar);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        HibernateUtil.getInstance().closeSession();
    }
}