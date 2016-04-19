package by.academy.it;

import by.academy.it.rentacar.beans.Fuel;
import by.academy.it.rentacar.beans.Price;
import by.academy.it.rentacar.dao.FuelDAO;
import by.academy.it.rentacar.dao.PriceDAO;
import by.academy.it.rentacar.enums.Transmission;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Unit-test class PriceDAO
 *
 * Created by Nata on 18.04.2016.
 */
public class PriceDAOTest {

  private static FuelDAO fuelDAO = FuelDAO.getInstance();
  private static Fuel testFuel;
  private static Price testPrice;
  private static Price expectedPrice;
  private static PriceDAO priceDAO = PriceDAO.getInstance();

  @BeforeClass
  public static void setUp() throws Exception {
    // add new fuel
    testFuel = new Fuel();
    testFuel.setName("new test");
    fuelDAO.add(testFuel);
    ArrayList<Fuel>  fuelList = fuelDAO.getAll();
    Fuel expectedFuel = fuelList.get(fuelList.size()-1);
    testFuel.setId(expectedFuel.getId());
    // creature new
    testPrice = new Price();
    testPrice.setName("test");
    testPrice.setTransmission(Transmission.AUTO);
    testPrice.setFuelId(testFuel.getId());
    testPrice.setCostOfDay(new BigDecimal(10).setScale(2));
    testPrice.setDiscount(new BigDecimal(1.5).setScale(4, BigDecimal.ROUND_HALF_UP));
  }

  @Test
  public void addPriceTest() throws Exception {
    priceDAO.add(testPrice);

    expectedPrice = priceDAO.getByTransmissionAndFuel(Transmission.AUTO, testFuel.getId());
    Assert.assertEquals("Add price: name", true, testPrice.getName().equals(expectedPrice.getName()));
    Assert.assertEquals("Add price: transmission", testPrice.getTransmission(), expectedPrice.getTransmission());
    Assert.assertEquals("Add price: fuels id", testPrice.getFuelId(), expectedPrice.getFuelId());
    Assert.assertEquals("Add price: cost of day", 0, testPrice.getCostOfDay().compareTo(expectedPrice.getCostOfDay()));
    Assert.assertEquals("Add price: discount", 0, testPrice.getDiscount().compareTo(expectedPrice.getDiscount()));
    testPrice.setId(expectedPrice.getId());
  }

  @AfterClass
  public static void tearDown() throws Exception {
    priceDAO.delete(testPrice);
    fuelDAO.delete(testFuel);
  }
}