package by.academy.it;

import by.academy.it.rentacar.actions.RatingService;
import by.academy.it.rentacar.dao.RatingDAO;
import by.academy.it.rentacar.entity.Rating;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

/**
 * Created by Nata on 23.05.2016.
 */
public class RatingServiceTest {
    @Test
    public void getListRating() throws Exception {
        List<Rating> list = RatingService.getInstance().getListRating();
        Assert.assertNotNull(list);

        int count = (int) RatingDAO.getInstance().count();
        Assert.assertEquals(count, list.size());
    }

}