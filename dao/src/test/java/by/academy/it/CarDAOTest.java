package by.academy.it;

import by.academy.it.rentacar.dao.ICarDAO;
import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.enums.ElementsPerPage;
import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.viewobject.CarVO;
import by.academy.it.rentacar.viewobject.FilterVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Nata on 14.05.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testDaoContext.xml")
@Transactional
public class CarDAOTest {

    @Autowired
    private ICarDAO carDAO;

    @Test
    public void searchCarTest() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dateForTest = sdf.parse(sdf.format(new Date()));
        // the list of the filter
        int foreignKey = 1;
        FilterVO filterVO = new FilterVO();
        filterVO.setRecordPerPage(ElementsPerPage.FIVE.getValue());
        filterVO.setByDate(dateForTest);
        filterVO.setFromDate(dateForTest);
        filterVO.setTransmission(Transmission.AUTO.getName());
        filterVO.setFuelId(foreignKey);
        filterVO.setTypeId(foreignKey);
        filterVO.setRatingId(foreignKey);

        List<CarVO> list = carDAO.searchCar(filterVO, 1);
        int countCar = carDAO.countCarByFilter(filterVO);

        Assert.assertNotNull(list);
        Assert.assertNotNull(countCar);
        Assert.assertEquals(list.size(), countCar);
    }

    @Test
    public void getAllCarsTest() throws Exception{
        List<Car> list = (ArrayList<Car>) carDAO.getAll();
        long count = carDAO.count();

        Assert.assertNotNull(list);
        Assert.assertNotNull(count);
        Assert.assertEquals(list.size(), count);
    }
}