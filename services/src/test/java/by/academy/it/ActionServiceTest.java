package by.academy.it;

import by.academy.it.rentacar.actions.ActionService;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.ModelAndMark;
import by.academy.it.rentacar.dao.FuelDAO;
import by.academy.it.rentacar.enums.Transmission;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit-test class ActionService
 *
 * Created by Nata on 18.04.2016.
 */
public class ActionServiceTest {
  private ActionService actionService = ActionService.getInstance();

  @Test
  public void getListTransmissionTest() throws Exception {
    ArrayList<Transmission> transmissionsList = actionService.getListTransmission();
    Assert.assertNotNull(transmissionsList);
    Assert.assertEquals("Size of the list of the transmissions", 2, transmissionsList.size());
  }

  @Test
  public void getListFuelTest() throws Exception {
    ArrayList<Fuel> fuelsListTest = actionService.getListFuel();
    ArrayList<Fuel> fuelsListExpected = (ArrayList<Fuel>) FuelDAO.getInstance().getAll();
    Assert.assertNotNull(fuelsListTest);
    Assert.assertEquals("Size of the list of the fuels", fuelsListTest.size(), fuelsListExpected.size());
  }

  @Test
  public void getListModelTest() throws Exception {
    ArrayList<ModelAndMark> modelsListTest = actionService.getListModel();
   // ArrayList<ModelAndMark> modelsListExpected = ModelAndMarkDAO.getInstance().getAll();
    Assert.assertNotNull(modelsListTest);
   // Assert.assertEquals("Size of the list of the fuels", modelsListTest.size(), modelsListExpected.size());
  }

}