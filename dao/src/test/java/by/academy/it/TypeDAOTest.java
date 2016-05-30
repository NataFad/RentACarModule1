package by.academy.it;

import by.academy.it.rentacar.dao.ITypeDAO;
import by.academy.it.rentacar.entity.Type;
import org.junit.Assert;
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

import java.math.BigDecimal;

/**
 * Created by Nata on 07.05.2016.
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
public class TypeDAOTest {

    @Autowired
    private ITypeDAO typeDAO;

    @Test
    public void getByIdTest() throws Exception {
        Type type = new Type("test", new BigDecimal(5).setScale(2, BigDecimal.ROUND_HALF_UP),
                new BigDecimal(1.3).setScale(2, BigDecimal.ROUND_HALF_UP));
        typeDAO.saveOrUpdate(type);

        Type testType = typeDAO.getById(type.getId());

        Assert.assertNotNull(testType);
        Assert.assertEquals(testType, type);

        typeDAO.delete(type);
    }
}