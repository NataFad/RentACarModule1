package by.academy.it;

import by.academy.it.rentacar.dao.IRatingDAO;
import by.academy.it.rentacar.entity.Rating;
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
 * Created by Nata on 08.05.2016.
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
public class RatingDAOTest {

    @Autowired
    private IRatingDAO ratingDAO;

    @Test
    public void getByIdTest() throws Exception {
        Rating rating = new Rating("test", new BigDecimal(5).setScale(2, BigDecimal.ROUND_HALF_UP));
        ratingDAO.saveOrUpdate(rating);

        Rating ratingTest = ratingDAO.getById(rating.getId());

        Assert.assertNotNull(ratingTest);
        Assert.assertEquals(ratingTest, rating);

        ratingDAO.delete(rating);
    }
}