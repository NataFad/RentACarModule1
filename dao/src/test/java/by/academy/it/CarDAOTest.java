package by.academy.it;

import by.academy.it.rentacar.beans.Car;
import by.academy.it.rentacar.beans.Price;
import by.academy.it.rentacar.dao.CarDAO;
import by.academy.it.rentacar.dao.PriceDAO;
import by.academy.it.rentacar.enums.Transmission;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

/**
 * Unit-test class CarDAO
 *
 * Created by Nata on 12.04.2016.
 */
public class CarDAOTest {

    private CarDAO carDAO;
    private Car testCar;
    private Car expectedCar;
    private ArrayList<Car> carList;

    @Before
    public void setUp() throws Exception {
        int foreign_id = 1;
        Price price = PriceDAO.getInstance().getByTransmissionAndFuel(Transmission.AUTO, foreign_id);
        carDAO  = CarDAO.getInstance();
        testCar = new Car();
        testCar.setRegistrationNumber("01-01 TE");
        testCar.setTransmission(Transmission.AUTO);
        testCar.setRatingId(foreign_id);
        testCar.setModelAndMarkId(foreign_id);
        testCar.setPriceId(price.getId());
        testCar.setFuelId(foreign_id);
        testCar.setCostOfDay(new BigDecimal(10).setScale(2));
        testCar.setDiscount(new BigDecimal(1.2).setScale(4, RoundingMode.HALF_UP));
        testCar.setTypeId(foreign_id);
        testCar.setDescription("test");
    }

    @Test
    public void addCarTest() throws Exception {
        carDAO.add(testCar);
        carList = carDAO.getAll();
        expectedCar = carList.get(carList.size()-1);

        Assert.assertEquals("Add car: registration number", true, testCar.getRegistrationNumber().equals(expectedCar.getRegistrationNumber()));
        Assert.assertEquals("Add car: transmission", testCar.getTransmission(), expectedCar.getTransmission());
        Assert.assertEquals("Add car: rating_id", testCar.getRatingId(), expectedCar.getRatingId());
        Assert.assertEquals("Add car: model and mark id", testCar.getModelAndMarkId(), expectedCar.getModelAndMarkId());
        Assert.assertEquals("Add car: price id", testCar.getPriceId(), expectedCar.getPriceId());
        Assert.assertEquals("Add car: fuels id", testCar.getFuelId(), expectedCar.getFuelId());
        Assert.assertEquals("Add car: cost of day", 0, testCar.getCostOfDay().compareTo(expectedCar.getCostOfDay()));
        Assert.assertEquals("Add car: discount", 0, testCar.getDiscount().compareTo(expectedCar.getDiscount()));
        Assert.assertEquals("Add car: description", testCar.getDescription(), expectedCar.getDescription());
        Assert.assertEquals("Add car: types id", testCar.getTypeId(), expectedCar.getTypeId());

        testCar.setId(expectedCar.getId());
    }

    @After
    public void tearDown() throws Exception {
        carDAO.delete(testCar);
    }

}
