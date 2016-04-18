package by.academy.it;

import by.academy.it.rentacar.beans.ModelAndMark;
import by.academy.it.rentacar.dao.ModelAndMarkDAO;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Unit-test class ModelAndMarkDAO
 *
 * Created by Nata on 18.04.2016.
 */
public class ModelAndMarkDAOTest {

  private static ModelAndMark testModel;
  private static ModelAndMark expectedModel;
  private static ModelAndMarkDAO modelDAO;
  private static ArrayList<ModelAndMark> listModels;

  @BeforeClass
  public static void setUp() throws Exception {
    modelDAO = ModelAndMarkDAO.getInstance();
    testModel = new ModelAndMark();
    testModel.setMark("test mark");
    testModel.setModel("test model");
  }

  @Test
  public void add() throws Exception {
    modelDAO.add(testModel);
  }

  @Test
  public void getAll() throws Exception {
    listModels = modelDAO.getAll();
    expectedModel = listModels.get(listModels.size()-1);

    Assert.assertEquals("Add model and mark: mark", true, testModel.getMark().equals(expectedModel.getMark()));
    Assert.assertEquals("Add model and mark: model", true, testModel.getModel().equals(expectedModel.getModel()));

    testModel.setId(expectedModel.getId());
  }

  @AfterClass
  public static void tearDown() throws Exception {
    modelDAO.delete(testModel);
  }
}