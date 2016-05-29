package by.academy.it;

import by.academy.it.rentacar.actions.FuelService;
import by.academy.it.rentacar.dao.FuelDAO;
import by.academy.it.rentacar.entity.Fuel;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Created by Nata on 23.05.2016.
 */
@Ignore
public class FuelServiceTest {
    private static FuelDAO fuelDAO;
    @Test
    public void getListFuel() throws Exception {
        List<Fuel> list = FuelService.getInstance().getListFuel();
        Assert.assertNotNull(list);
        int count = (int) fuelDAO.count();
        Assert.assertEquals(count, list.size());
    }

}