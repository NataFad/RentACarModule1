package by.academy.it;

import by.academy.it.rentacar.actions.IPriceService;
import by.academy.it.rentacar.dao.IFuelDAO;
import by.academy.it.rentacar.dao.IPriceDAO;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.enums.Transmission;
import org.junit.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Unit-test class PriceDAO
 *
 * Created by Nata on 18.04.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@Ignore
public class PriceServiceTest {

  @Autowired
  private IFuelDAO fuelDAO;

  private static Fuel testFuel;
  private static Price testPrice;
  private static Price expectedPrice;

  @Autowired
  private IPriceDAO priceDAO;
  @Autowired
  private IPriceService priceService;

  @Before
  public void setUp() throws Exception {
    // add new fuel
    testFuel = new Fuel();
    testFuel.setName("new test");
    fuelDAO.saveOrUpdate(testFuel);
    ArrayList<Fuel>  fuelList = (ArrayList<Fuel>) fuelDAO.getAll();
    Fuel expectedFuel = fuelList.get(fuelList.size()-1);
    testFuel.setId(expectedFuel.getId());
    // creature new price
    testPrice = new Price();
    testPrice.setName("test + manual");
    testPrice.setTransmission(Transmission.MANUAL);
    testPrice.setFuel(testFuel);
    testPrice.setCostOfDay(new BigDecimal(10).setScale(2));
    testPrice.setDiscount(new BigDecimal(1.5).setScale(4, BigDecimal.ROUND_HALF_UP));
    priceDAO.saveOrUpdate(testPrice);
  }

  @Test
  public void getByTransmissionAndFuelTest() throws Exception {
    expectedPrice = priceService.getByTransmissionAndFuel(Transmission.MANUAL, testFuel);
    Assert.assertNotNull(expectedPrice);
    Assert.assertEquals("Add price: name", true, testPrice.getName().equals(expectedPrice.getName()));
    Assert.assertEquals("Add price: transmission", testPrice.getTransmission(), expectedPrice.getTransmission());
    Assert.assertEquals("Add price: fuels id", testPrice.getFuel(), expectedPrice.getFuel());
    Assert.assertEquals("Add price: cost of day", 0, testPrice.getCostOfDay().compareTo(expectedPrice.getCostOfDay()));
    Assert.assertEquals("Add price: discount", 0, testPrice.getDiscount().compareTo(expectedPrice.getDiscount()));
    testPrice.setId(expectedPrice.getId());
  }

  @After
  public void tearDown() throws Exception {
    priceDAO.delete(testPrice);
    fuelDAO.delete(testFuel);
  }
}