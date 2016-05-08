package by.academy.it;

import by.academy.it.rentacar.dao.RatingDAO;
import by.academy.it.rentacar.entity.Rating;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by Nata on 08.05.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 */
public class RatingDAOTest {

    @Test
    public void getByIdTest() throws Exception {
        RatingDAO ratingDAO = RatingDAO.getInstance();
        Rating rating = new Rating("test", new BigDecimal(5).setScale(2, BigDecimal.ROUND_HALF_UP));
        ratingDAO.saveOrUpdate(rating);

        Rating  ratingTest = ratingDAO.getById(rating.getId());

        Assert.assertNotNull(ratingTest);
        Assert.assertEquals(ratingTest, rating);

        ratingDAO.delete(rating);
    }
}