package by.academy.it;

import by.academy.it.rentacar.dao.IModelAndMarkDAO;
import by.academy.it.rentacar.entity.ModelAndMark;
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

import java.util.ArrayList;
import java.util.List;

/**
 * Unit-test class ModelAndMarkDAO
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
public class ModelAndMarkDAOTest {

    @Autowired
    private IModelAndMarkDAO modelDAO;
    private static ModelAndMark testModel;

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

    private void getAllModelsTest() throws Exception {
        List<ModelAndMark> listModels = (ArrayList<ModelAndMark>) modelDAO.getAll();
        ModelAndMark expectedModel = listModels.get(listModels.size() - 1);

        Assert.assertEquals("Add model and mark: mark", true, testModel.getMark().equals(expectedModel.getMark()));
        Assert.assertEquals("Add model and mark: model", true, testModel.getModel().equals(expectedModel.getModel()));
    }

    private void getModelByIdTest() throws Exception {
        ModelAndMark expectedModel = modelDAO.getById(testModel.getId());

        Assert.assertNotNull(expectedModel);
        Assert.assertEquals(expectedModel, testModel);
    }

    private void deleteModelTest() throws Exception {
        modelDAO.delete(testModel);
    }
}