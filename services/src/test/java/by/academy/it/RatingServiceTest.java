package by.academy.it;

import by.academy.it.rentacar.actions.IRatingService;
import by.academy.it.rentacar.configuration.HibernateConfiguration;
import by.academy.it.rentacar.dao.IRatingDAO;
import by.academy.it.rentacar.entity.Rating;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nata on 23.05.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@Transactional
public class RatingServiceTest {

    @Autowired
    private IRatingDAO ratingDAO;
    @Autowired
    private IRatingService ratingService;

    @Test
    public void getListRating() throws Exception {
        List<Rating> list = ratingService.getListRating();
        Assert.assertNotNull(list);

        int count = (int) ratingDAO.count();
        Assert.assertEquals(count, list.size());
    }

}