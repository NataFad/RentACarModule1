package by.academy.it;

import by.academy.it.rentacar.actions.CarService;
import by.academy.it.rentacar.dao.*;
import by.academy.it.rentacar.entity.*;
import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.util.HibernateUtil;
import by.academy.it.rentacar.viewobject.CarViewObject;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nata on 19.04.2016.
 */
public class CarServiceTest {

  private static CarService carService = CarService.getInstance();
  private static CarDAO carDAO = CarDAO.getInstance();
  private static Car testCar;
  private static Transmission transTest = Transmission.AUTO;

  @BeforeClass
  public static void setUp() throws Exception {
    int foreign_id = 1;
    Fuel fuel = FuelDAO.getInstance().getById(foreign_id);
    Rating rating = RatingDAO.getInstance().getById(foreign_id);
    ModelAndMark model = ModelAndMarkDAO.getInstance().getById(foreign_id);
    Type type = TypeDAO.getInstance().getById(foreign_id);

    Price price = PriceDAO.getInstance().getByTransmissionAndFuel(transTest, fuel);
    carDAO  = CarDAO.getInstance();
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
  }

  @Test
  public void CarServiceTest() throws Exception{
    registerCarTest();
    getAllCarsServiceTest();
  }

  private void registerCarTest() throws Exception {
    HibernateUtil.getInstance().getSession().beginTransaction();

    HashMap<String, String> parametresCar = new HashMap<String, String>();
    parametresCar.put("transmission", transTest.name());
    parametresCar.put("fuelId", "1");
    parametresCar.put("typeId", "1");
    parametresCar.put("ratingId", "1");
    parametresCar.put("registrationNumber",	"00-00 TE");
    parametresCar.put("modelId", "1");
    parametresCar.put("description", "test");

    testCar = carService.registeredCar(parametresCar);
    Assert.assertNotNull(testCar);
  }

  private void getAllCarsServiceTest() throws Exception {
    HibernateUtil.getInstance().getSession().beginTransaction();
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

    HibernateUtil.getInstance().getSession().beginTransaction();
    List<CarViewObject> list = CarService.getInstance().getSearchCar(fromDate, byDate, filterValues);
    Assert.assertNotNull(list);
    CarViewObject carVO = list.get(list.size()-1);

    Assert.assertEquals("Registered car: registration number", true, testCar.getRegistrationNumber().equals(carVO.getRegistrationNumber()));
    Assert.assertEquals("Registered car: fuel", testCar.getFuel().getName(), carVO.getFuel());
    Assert.assertEquals("Registered car: type", testCar.getType().getName(), carVO.getType());
    Assert.assertEquals("Registered car: rating", testCar.getRating().getName(), carVO.getRating());
    Assert.assertEquals("Registered car: description", testCar.getDescription(), carVO.getDescription());
  }

  @AfterClass
  public static void tearDown() throws Exception {
    HibernateUtil.getInstance().getSession().beginTransaction();
    carDAO.delete(testCar);
    HibernateUtil.getInstance().getSession().getTransaction().commit();
  }
}