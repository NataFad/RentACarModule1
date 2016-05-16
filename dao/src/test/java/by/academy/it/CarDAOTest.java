package by.academy.it;

import by.academy.it.rentacar.dao.CarDAO;
import by.academy.it.rentacar.viewobject.CarViewObject;
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
        filterValues.put("ratingId", "1");
        filterValues.put("orderBy", "F.name ASC, MAndM.mark ASC");

        List<CarViewObject> list = CarDAO.getInstance().searchCar(dateForTest, dateForTest, filterValues);
        /**for (CarViewObject car:list){
            System.out.println(car);
        }
        */

        BigInteger countCar = CarDAO.getInstance().countCarByFilter(dateForTest, dateForTest, filterValues);

        Assert.assertNotNull(list);
        Assert.assertNotNull(countCar);
        Assert.assertEquals(BigInteger.valueOf(list.size()), countCar);
    }

    @AfterClass
    public static void tearDown() throws Exception {
      //  HibernateUtil.getInstance().closeSession();
    }
}