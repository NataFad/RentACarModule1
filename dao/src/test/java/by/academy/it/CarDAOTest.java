package by.academy.it;

import by.academy.it.rentacar.beans.Car;
import by.academy.it.rentacar.dao.CarDAO;
import by.academy.it.rentacar.enums.Transmission;
import org.junit.*;

import java.math.BigDecimal;
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

    @Ignore
    public void setUp() throws Exception {
        int foreign_id = 1;
        carDAO  = CarDAO.getInstance();
        testCar = new Car();
        testCar.setRegistrationNumber("00-00 TE");
        testCar.setTransmission(Transmission.AUTO);
        testCar.setRatingId(foreign_id);
        testCar.setModelAndMarkId(foreign_id);
        testCar.setPriceId(foreign_id);
        testCar.setFuelId(foreign_id);
        testCar.setCostOfDay(new BigDecimal(10));
        testCar.setDiscount(new BigDecimal(1.2));
        testCar.setTypeId(foreign_id);
        testCar.setDescription("test");
    }

    @Ignore
    public void addCarTest() throws Exception {
        carDAO.add(testCar);
        carList = carDAO.getAll();
        expectedCar = carList.get(carList.size()-1);
        Assert.assertEquals("Add car: registration number", testCar.getRegistrationNumber(), expectedCar.getRegistrationNumber());
        Assert.assertEquals("Add car: transmission", testCar.getTransmission(), expectedCar.getTransmission());
        Assert.assertEquals("Add car: rating_id", testCar.getRatingId(), expectedCar.getRatingId());
        Assert.assertEquals("Add car: model and mark id", testCar.getModelAndMarkId(), expectedCar.getModelAndMarkId());
        Assert.assertEquals("Add car: price id", testCar.getPriceId(), expectedCar.getPriceId());
        Assert.assertEquals("Add car: fuels id", testCar.getFuelId(), expectedCar.getFuelId());
        Assert.assertEquals("Add car: cost of day", testCar.getCostOfDay(), expectedCar.getCostOfDay());
        Assert.assertEquals("Add car: discount", testCar.getDiscount(), expectedCar.getDiscount());
        Assert.assertEquals("Add car: description", testCar.getDescription(), expectedCar.getDescription());
        Assert.assertEquals("Add car: types id", testCar.getTypeId(), expectedCar.getTypeId());

        testCar.setId(expectedCar.getId());
    }

    @Ignore
    public void tearDown() throws Exception {
        carDAO.delete(testCar);
    }



}
