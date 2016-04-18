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
  public void addTest() throws Exception {
    fuelDAO.add(testFuel);
    fuelList = fuelDAO.getAll();
    expectedFuel = fuelList.get(fuelList.size()-1);
    int countFuels = fuelDAO.count();

    Assert.assertEquals("Add fuel: name", true, testFuel.getName().equals(expectedFuel.getName()));
    Assert.assertEquals("Count fuels", fuelList.size(), countFuels);
    testFuel.setId(expectedFuel.getId());
  }

  @Test
  public void updateTest() throws Exception{
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

