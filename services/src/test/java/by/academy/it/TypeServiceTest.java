package by.academy.it;

import by.academy.it.rentacar.actions.TypeService;
import by.academy.it.rentacar.dao.TypeDAO;
import by.academy.it.rentacar.entity.Type;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Nata on 23.05.2016.
 */
public class TypeServiceTest {
    @Test
    public void getListType() throws Exception {
        List<Type> list = TypeService.getInstance().getListType();
        Assert.assertNotNull(list);
        int count = (int) TypeDAO.getInstance().count();
        Assert.assertEquals(count, list.size());
    }

}