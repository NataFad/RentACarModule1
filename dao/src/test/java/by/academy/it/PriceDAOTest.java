package by.academy.it;

import by.academy.it.rentacar.dao.FuelDAO;
import by.academy.it.rentacar.dao.PriceDAO;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.enums.Transmission;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Unit-test class PriceDAO
 * <p>
 * Created by Nata on 18.04.2016.
 */
public class PriceDAOTest {

    private static FuelDAO fuelDAO = FuelDAO.getInstance();
    private static Fuel testFuel;
    private static Price testPrice;
    private static PriceDAO priceDAO = PriceDAO.getInstance();

    @BeforeClass
    public static void setUp() throws Exception {
        // add new fuel
        testFuel = new Fuel();
        testFuel.setName("new test");
        fuelDAO.saveOrUpdate(testFuel);

        System.out.println(testFuel);
        // creature new price
        testPrice = new Price();
        testPrice.setName("test price");
        testPrice.setTransmission(Transmission.AUTO);
        testPrice.setFuel(testFuel);
        testPrice.setCostOfDay(new BigDecimal(10).setScale(2));
        testPrice.setDiscount(new BigDecimal(1.5).setScale(4, BigDecimal.ROUND_HALF_UP));

    }

    @Test
    public void getByTransmissionAndFuelTest() throws Exception {
        priceDAO.saveOrUpdate(testPrice);
        Price expectedPrice = priceDAO.getByTransmissionAndFuel(Transmission.AUTO, testFuel);
        Assert.assertEquals("Add price: name", true, testPrice.getName().equals(expectedPrice.getName()));
        Assert.assertEquals("Add price: transmission", testPrice.getTransmission(), expectedPrice.getTransmission());
        Assert.assertEquals("Add price: fuels id", testPrice.getFuel(), expectedPrice.getFuel());
        Assert.assertEquals("Add price: cost of day", 0, testPrice.getCostOfDay().compareTo(expectedPrice.getCostOfDay()));
        Assert.assertEquals("Add price: discount", 0, testPrice.getDiscount().compareTo(expectedPrice.getDiscount()));
    }

    @AfterClass
    public static void tearDown() throws Exception {
        priceDAO.delete(testPrice);
        //fuelDAO.delete(testFuel);
       // HibernateUtil.getInstance().closeSession();
    }
}