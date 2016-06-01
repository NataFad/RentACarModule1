package by.academy.it;

import by.academy.it.rentacar.actions.ICarService;
import by.academy.it.rentacar.configuration.HibernateConfiguration;
import by.academy.it.rentacar.dao.*;
import by.academy.it.rentacar.entity.*;
import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.viewobject.CarVO;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nata on 19.04.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@Transactional
public class CarServiceTest {

    @Autowired
    private ICarService carService;
    @Autowired
    private ICarDAO carDAO;
    @Autowired
    private IFuelDAO fuelDAO;
    @Autowired
    private IRatingDAO ratingDAO;
    @Autowired
    private IModelAndMarkDAO modelAndMarkDAO;
    @Autowired
    private ITypeDAO typeDAO;
    @Autowired
    private IPriceDAO priceDAO;

    private static Car testCar;
    private static Transmission transTest = Transmission.AUTO;

    @Test
    public void CarServiceTest() throws Exception {
        int foreign_id = 1;
        Fuel fuel = fuelDAO.getById(foreign_id);
        Rating rating = ratingDAO.getById(foreign_id);
        ModelAndMark model = modelAndMarkDAO.getById(foreign_id);
        Type type = typeDAO.getById(foreign_id);

        Price price = priceDAO.getByTransmissionAndFuel(transTest, fuel);
        testCar = new Car();
        testCar.setRegistrationNumber("00-00 TE");
        testCar.setTransmission(transTest);
        testCar.setRating(rating);
        testCar.setModel(model);
        testCar.setPrice(price);
        testCar.setFuel(fuel);
        testCar.setCostOfDay(new BigDecimal(15).setScale(2));
        testCar.setDiscount(new BigDecimal(1.5).setScale(4, RoundingMode.HALF_UP));
        testCar.setType(type);
        testCar.setDescription("test");

        registerCarTest();
        getAllCarsServiceTest();

        carDAO.delete(testCar);
    }

    private void registerCarTest() throws Exception {
        HashMap<String, String> parametresCar = new HashMap<String, String>();
        parametresCar.put("transmission", transTest.name());
        parametresCar.put("fuelId", "1");
        parametresCar.put("typeId", "1");
        parametresCar.put("ratingId", "1");
        parametresCar.put("registrationNumber", "00-00 TE");
        parametresCar.put("modelId", "1");
        parametresCar.put("description", "test");

        testCar = carService.registeredCar(parametresCar);
        Assert.assertNotNull(testCar);
    }

    private void getAllCarsServiceTest() throws Exception {
        ArrayList<Car> carsList = carService.getAllCars();
        Assert.assertNotNull(carsList);
        // period
        Date fromDate = Date.valueOf("2016-01-01");
        Date byDate = Date.valueOf("2016-01-01");
        HashMap<String, String> filterValues = new HashMap<String, String>();
        // Transmission
        filterValues.put("transmission", transTest.toString());
        // Fuels
        String foreign_id = "1";
        filterValues.put("fuelId", foreign_id);
        // Type
        filterValues.put("typeId", foreign_id);
        // Rating
        filterValues.put("ratingId", foreign_id);
        filterValues.put("page", "1");
        filterValues.put("recordsPerPage", "10");

        List<CarVO> list = carService.getSearchCar(fromDate, byDate, filterValues);
        Assert.assertNotNull(list);

        CarVO carVO = list.get(list.size() - 1);

        Assert.assertEquals("Registered car: registration number", true, testCar.getRegistrationNumber().equals(carVO.getRegistrationNumber()));
        Assert.assertEquals("Registered car: fuel", testCar.getFuel().getName(), carVO.getFuel());
        Assert.assertEquals("Registered car: type", testCar.getType().getName(), carVO.getType());
        Assert.assertEquals("Registered car: rating", testCar.getRating().getName(), carVO.getRating());
        Assert.assertEquals("Registered car: description", testCar.getDescription(), carVO.getDescription());
    }
}