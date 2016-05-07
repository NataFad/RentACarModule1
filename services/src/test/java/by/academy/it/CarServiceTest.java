package by.academy.it;

import by.academy.it.rentacar.actions.CarService;
import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.dao.CarDAO;
import by.academy.it.rentacar.dao.PriceDAO;
import by.academy.it.rentacar.enums.Transmission;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Nata on 19.04.2016.
 */
public class CarServiceTest {

  private static CarService carService = CarService.getInstance();
  private static CarDAO carDAO = CarDAO.getInstance();
  private static Car testCar;

  @BeforeClass
  public static void setUp() throws Exception {
    int foreign_id = 1;
    Price price = PriceDAO.getInstance().getByTransmissionAndFuel(Transmission.MANUAL, foreign_id);
    carDAO  = CarDAO.getInstance();
    testCar = new Car();
    testCar.setRegistrationNumber("00-00 TE");
    testCar.setTransmission(Transmission.MANUAL);
    testCar.setRatingId(foreign_id);
    testCar.setModelAndMarkId(foreign_id);
    testCar.setPriceId(price.getId());
    testCar.setFuelId(foreign_id);
    testCar.setCostOfDay(new BigDecimal(15).setScale(2));
    testCar.setDiscount(new BigDecimal(1.5).setScale(4, RoundingMode.HALF_UP));
    testCar.setTypeId(foreign_id);
    testCar.setDescription("test");
  }

  @Test
  public void registerCarTest() throws Exception {
    int success = carService.register(testCar);
    Assert.assertEquals("Registered the car", 1, success);
  }

  @Test
  public void getAllCarsServiceTest() throws Exception {
    ArrayList<Car> carsList = carService.getAllCars();
    Assert.assertNotNull(carsList);
    Car car = carsList.get(carsList.size()-1);
    testCar.setId(car.getId());

    // period
    Date fromDate = Date.valueOf("2016-01-01");
    Date byDate = Date.valueOf("2016-01-01");
    HashMap<String, String> filterValues = new HashMap<String, String>();
    // Transmission
    filterValues.put("transmission", Transmission.MANUAL.toString());
    // Fuels
    String foreign_id = "1";
    filterValues.put("fuelId", foreign_id);
    // Type
    filterValues.put("typeId", foreign_id);
    // Rating
    filterValues.put("ratingId", foreign_id);

    ArrayList<Car> list = CarService.getInstance().getSearchCar(fromDate, byDate, filterValues);
    Assert.assertNotNull(list);
    car = list.get(list.size()-1);

    Assert.assertEquals("Registered car: registration number", true, testCar.getRegistrationNumber().equals(car.getRegistrationNumber()));
    Assert.assertEquals("Registered car: price id", testCar.getPriceId(), car.getPriceId());
    Assert.assertEquals("Registered car: cost of day", 0, testCar.getCostOfDay().compareTo(car.getCostOfDay()));
    Assert.assertEquals("Registered car: discount", 0, testCar.getDiscount().compareTo(car.getDiscount()));
    Assert.assertEquals("Registered car: description", testCar.getDescription(), car.getDescription());
  }

  @AfterClass
  public static void tearDown() throws Exception {
    carDAO.delete(testCar);
  }
}