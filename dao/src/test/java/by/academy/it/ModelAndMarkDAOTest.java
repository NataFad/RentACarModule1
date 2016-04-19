package by.academy.it;

import by.academy.it.rentacar.beans.ModelAndMark;
import by.academy.it.rentacar.dao.ModelAndMarkDAO;
import org.junit.*;

import java.util.ArrayList;

/**
 * Unit-test class ModelAndMarkDAO
 *
 * Created by Nata on 18.04.2016.
 */
public class ModelAndMarkDAOTest {

  private static ModelAndMark testModel;
  private static ModelAndMarkDAO modelDAO = ModelAndMarkDAO.getInstance();

  @BeforeClass
  public static void setUp() throws Exception {
    testModel = new ModelAndMark();
    testModel.setMark("test mark");
    testModel.setModel("test model");
  }

  @Test
  public void addModelTest() throws Exception {
    modelDAO.add(testModel);

    ArrayList<ModelAndMark> listModels = modelDAO.getAll();
    ModelAndMark expectedModel = listModels.get(listModels.size()-1);

    Assert.assertEquals("Add model and mark: mark", true, testModel.getMark().equals(expectedModel.getMark()));
    Assert.assertEquals("Add model and mark: model", true, testModel.getModel().equals(expectedModel.getModel()));

    testModel.setId(expectedModel.getId());
  }

  @AfterClass
  public static void tearDown() throws Exception {
    modelDAO.delete(testModel);
  }
}