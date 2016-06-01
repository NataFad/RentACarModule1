package by.academy.it;

import by.academy.it.rentacar.actions.IActionService;
import by.academy.it.rentacar.configuration.HibernateConfiguration;
import by.academy.it.rentacar.dao.IFuelDAO;
import by.academy.it.rentacar.dao.IModelAndMarkDAO;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.ModelAndMark;
import by.academy.it.rentacar.enums.Transmission;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Unit-test class ActionService
 * <p>
 * Created by Nata on 18.04.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@Transactional
public class ActionServiceTest {

    @Autowired
    private IActionService actionService;
    @Autowired
    private IFuelDAO fuelDAO;
    @Autowired
    private IModelAndMarkDAO modelDAO;

    @Test
    public void getListTransmissionTest() throws Exception {
        ArrayList<Transmission> transmissionsList = actionService.getListTransmission();
        Assert.assertNotNull(transmissionsList);
        Assert.assertEquals("Size of the list of the transmissions", 2, transmissionsList.size());
    }

    @Test
    public void getListFuelTest() throws Exception {
        ArrayList<Fuel> fuelsListTest = actionService.getListFuel();
        ArrayList<Fuel> fuelsListExpected = (ArrayList<Fuel>) fuelDAO.getAll();
        Assert.assertNotNull(fuelsListTest);
        Assert.assertEquals("Size of the list of the fuels", fuelsListTest.size(), fuelsListExpected.size());
    }

    @Test
    public void getListModelTest() throws Exception {
        ArrayList<ModelAndMark> modelsListTest = actionService.getListModel();
        ArrayList<ModelAndMark> modelsListExpected = (ArrayList<ModelAndMark>) modelDAO.getAll();
        Assert.assertNotNull(modelsListTest);
        Assert.assertEquals("Size of the list of the fuels", modelsListTest.size(), modelsListExpected.size());
    }

}