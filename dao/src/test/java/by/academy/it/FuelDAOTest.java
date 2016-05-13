package by.academy.it;

import by.academy.it.rentacar.by.academy.it.rentacar.util.HibernateUtil;
import by.academy.it.rentacar.dao.FuelDAO;
import by.academy.it.rentacar.entity.Fuel;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit-test class FuelDAO
 * <p>
 * Created by Nata on 12.04.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 */
public class FuelDAOTest {

    private static FuelDAO fuelDAO;
    private static Fuel testFuel;
    private static Fuel expectedFuel;
    private static ArrayList<Fuel> fuelList;

    @BeforeClass
    public static void setUp() throws Exception {
        fuelDAO = FuelDAO.getInstance();
        testFuel = new Fuel();
        testFuel.setName("test");
    }

    @Test
    public void testFuelDAO() throws Exception {
        addFuelTest();
        getByIdTest();
        getAllFuelTest();
        countFuelTest();
        updateFuelTest();
        searchByNameTest();
        deleteFuelTest();
    }

    private void addFuelTest() throws Exception {
        fuelDAO.saveOrUpdate(testFuel);
    }

    private void getByIdTest(){
        expectedFuel = fuelDAO.getById(testFuel.getId());
        Assert.assertNotNull(expectedFuel);
        Assert.assertEquals(expectedFuel, testFuel);
    }

    private void getAllFuelTest() throws Exception {
        fuelList = (ArrayList<Fuel>) fuelDAO.getAll();
        expectedFuel = fuelList.get(fuelList.size() - 1);

        Assert.assertNotNull(fuelList);
        Assert.assertEquals("Add fuel: name", true, testFuel.getName().equals(expectedFuel.getName()));
    }

    private void countFuelTest() throws Exception {
        long countFuels = fuelDAO.count();
        fuelList = (ArrayList<Fuel>) fuelDAO.getAll();
        Assert.assertEquals("Count fuels", fuelList.size(), countFuels);
    }

    private void updateFuelTest() throws Exception {
        testFuel.setName("diesel+gaz");
        fuelDAO.saveOrUpdate(testFuel);
        expectedFuel = fuelDAO.get(testFuel.getId());
        Assert.assertEquals("Update fuel", true, testFuel.getName().equals(expectedFuel.getName()));
    }

    private void searchByNameTest() throws Exception {
        fuelList = (ArrayList<Fuel>) fuelDAO.searchByName("diesel+gaz");
        Assert.assertFalse(fuelList.isEmpty());

        expectedFuel = fuelList.get(0);
        Assert.assertNotNull(expectedFuel);
        Assert.assertEquals(expectedFuel, testFuel);
    }

    private void deleteFuelTest() throws Exception {
        fuelDAO.delete(testFuel);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        HibernateUtil.getInstance().closeSession();
    }
}

