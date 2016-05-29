package by.academy.it;

import by.academy.it.rentacar.dao.FuelDAO;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.exceptions.DAOException;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Unit-test class FuelDAO
 * <p>
 * Created by Nata on 12.04.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
//@Ignore
//@RunWith(SpringJUnit4ClassRunner.class)
////@ContextConfiguration(classes = HibernateConfiguration.class)
//@ContextConfiguration("/testDaoContext.xml")
//@Transactional
//@TestExecutionListeners(listeners = {DependencyInjectionTestExecutionListener.class,
//        DirtiesContextTestExecutionListener.class,
//        TransactionalTestExecutionListener.class
//})
public class FuelDAOTest {

    @Autowired
    public FuelDAO fuelDAO;

    private static Fuel testFuel;
    private static Fuel expectedFuel;
    private static ArrayList<Fuel> fuelList;

    @BeforeClass
    public static void setUp() throws Exception {
        testFuel = new Fuel();
        testFuel.setName("test");
    }

    @Test
    public void testFuelDAO() throws Exception {
        addFuelTest();
        getByIdTest();
        getTest();
        getAllFuelTest();
        countFuelTest();
        updateFuelTest();
        searchByNameTest();
        deleteFuelTest();
    }

    private void addFuelTest() throws Exception {
        fuelDAO.saveOrUpdate(testFuel);
    }

    private void getByIdTest() {
        expectedFuel = fuelDAO.getById(testFuel.getId());
        Assert.assertNotNull(expectedFuel);
        Assert.assertEquals(expectedFuel, testFuel);
    }

    private void getTest() throws DAOException {
        expectedFuel = fuelDAO.get(testFuel.getId());
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
        // HibernateUtil.getInstance().closeSession();
    }
}

