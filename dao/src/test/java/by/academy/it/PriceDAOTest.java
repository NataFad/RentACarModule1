package by.academy.it;

import by.academy.it.rentacar.dao.IFuelDAO;
import by.academy.it.rentacar.dao.IPriceDAO;
import by.academy.it.rentacar.dao.PriceDAO;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.enums.Transmission;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Unit-test class PriceDAO
 * <p>
 * Created by Nata on 18.04.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testDaoContext.xml")
@Transactional
@TransactionConfiguration(defaultRollback = true)
@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class
})
public class PriceDAOTest {

    @Autowired
    private IFuelDAO fuelDAO;
    @Autowired
    private IPriceDAO priceDAO;

    private static Fuel testFuel;
    private static Price testPrice;

    @BeforeClass
    public static void setUp() throws Exception {
        // add new fuel
        testFuel = new Fuel();
        testFuel.setName("new test");
        // creature new price
        testPrice = new Price();
        testPrice.setName("test price");
        testPrice.setTransmission(Transmission.AUTO);
        testPrice.setCostOfDay(new BigDecimal(10).setScale(2));
        testPrice.setDiscount(new BigDecimal(1.5).setScale(4, BigDecimal.ROUND_HALF_UP));
    }

    @Test
    public void getByTransmissionAndFuelTest() throws Exception {
        fuelDAO.saveOrUpdate(testFuel);

        testPrice.setFuel(testFuel);
        priceDAO.saveOrUpdate(testPrice);
        Price expectedPrice = priceDAO.getByTransmissionAndFuel(Transmission.AUTO, testFuel);
        Assert.assertEquals("Add price: name", true, testPrice.getName().equals(expectedPrice.getName()));
        Assert.assertEquals("Add price: transmission", testPrice.getTransmission(), expectedPrice.getTransmission());
        Assert.assertEquals("Add price: fuels id", testPrice.getFuel(), expectedPrice.getFuel());
        Assert.assertEquals("Add price: cost of day", 0, testPrice.getCostOfDay().compareTo(expectedPrice.getCostOfDay()));
        Assert.assertEquals("Add price: discount", 0, testPrice.getDiscount().compareTo(expectedPrice.getDiscount()));

        priceDAO.delete(testPrice);
        fuelDAO.delete(testFuel);
    }
}