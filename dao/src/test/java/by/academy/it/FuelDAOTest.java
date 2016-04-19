package by.academy.it;

import by.academy.it.rentacar.beans.Fuel;
import by.academy.it.rentacar.dao.FuelDAO;
import org.junit.*;

import java.util.ArrayList;

/**
 * Unit-test class FuelDAO
 *
 * Created by Nata on 12.04.2016.
 */
public class FuelDAOTest {

  private static FuelDAO fuelDAO;
  private static Fuel testFuel;
  private static Fuel expectedFuel;
  private static ArrayList<Fuel> fuelList;

  @BeforeClass
  public static void setUp() throws Exception {
    fuelDAO  = FuelDAO.getInstance();
    testFuel = new Fuel();
    testFuel.setName("test");
  }

  @Test
  public void addFuelTest() throws Exception {
    fuelDAO.add(testFuel);
  }

  @Test
  public void getAllFuelTest() throws Exception{
    fuelList = fuelDAO.getAll();
    expectedFuel = fuelList.get(fuelList.size()-1);

    Assert.assertNotNull(fuelList);
    Assert.assertEquals("Add fuel: name", true, testFuel.getName().equals(expectedFuel.getName()));
    testFuel.setId(expectedFuel.getId());
  }

  @Test
  public void countFuelTest() throws Exception{
    int countFuels = fuelDAO.count();
    fuelList = fuelDAO.getAll();
    Assert.assertEquals("Count fuels", fuelList.size(), countFuels);
  }

  @Test
  public void updateFuelTest() throws Exception{
    testFuel.setName("diesel+gaz");
    fuelDAO.update(testFuel);
    expectedFuel = fuelDAO.getById(testFuel.getId());
    Assert.assertEquals("Update fuel", true, testFuel.getName().equals(expectedFuel.getName()));
  }

  @AfterClass
  public static void tearDown() throws Exception {
    fuelDAO.delete(testFuel);
  }
}

