package by.academy.it;

import by.academy.it.rentacar.dao.TypeDAO;
import by.academy.it.rentacar.entity.Type;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Nata on 07.05.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 */
public class TypeDAOTest {

    private static TypeDAO typeDAO;
    @Test
    public void getByIdTest() throws Exception {
        Type type = new Type("test", new BigDecimal(5).setScale(2, BigDecimal.ROUND_HALF_UP),
                new BigDecimal(1.3).setScale(2, BigDecimal.ROUND_HALF_UP));
        typeDAO.saveOrUpdate(type);

        Type testType = typeDAO.getById(type.getId());

        Assert.assertNotNull(testType);
        Assert.assertEquals(testType, type);

        typeDAO.delete(type);
       // HibernateUtil.getInstance().closeSession();
    }
}