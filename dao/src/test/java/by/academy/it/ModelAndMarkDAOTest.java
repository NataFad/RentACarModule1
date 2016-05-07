package by.academy.it;

import by.academy.it.rentacar.by.academy.it.rentacar.util.HibernateUtil;
import by.academy.it.rentacar.dao.ModelAndMarkDAO;
import by.academy.it.rentacar.entity.ModelAndMark;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Unit-test class ModelAndMarkDAO
 * <p>
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
        saveModelTest();
        getAllModelsTest();
        getModelByIdTest();
        deleteModelTest();
    }

    private void saveModelTest() throws Exception {
        modelDAO.saveOrUpdate(testModel);
    }

    private void getAllModelsTest() throws Exception{
        List<ModelAndMark> listModels = (ArrayList<ModelAndMark>) modelDAO.getAll();
        ModelAndMark expectedModel = listModels.get(listModels.size() - 1);

        Assert.assertEquals("Add model and mark: mark", true, testModel.getMark().equals(expectedModel.getMark()));
        Assert.assertEquals("Add model and mark: model", true, testModel.getModel().equals(expectedModel.getModel()));

        testModel.setId(expectedModel.getId());
    }

    private void getModelByIdTest() throws Exception{
        ModelAndMark expectedModel = modelDAO.getById(testModel.getId());

        Assert.assertNotNull(expectedModel);
        Assert.assertEquals(expectedModel, testModel);
    }

    private void deleteModelTest() throws Exception{
        modelDAO.delete(testModel);
    }

    @AfterClass
    public static void tearDown() throws Exception {
        HibernateUtil.getInstance().closeSession();
    }
}