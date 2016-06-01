package by.academy.it;

import by.academy.it.rentacar.actions.IUserService;
import by.academy.it.rentacar.configuration.HibernateConfiguration;
import by.academy.it.rentacar.dao.IUserDAO;
import by.academy.it.rentacar.entity.UserEntity;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Nata on 21.05.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = HibernateConfiguration.class)
@Transactional
public class UserServiceTest {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserDAO userDAO;

    private static UserEntity userTest;
    private String password;

    @Before
    public void setUp() throws Exception {
        Calendar calendar = new GregorianCalendar(1971, 1, 21);
        userTest = new UserEntity();
        userTest.setName("test");
        userTest.setAccess(0);
        userTest.setBirthday(calendar.getTime());
        userTest.setEmail("nata.filimon@gmail.com");
        userTest.setLogin("testNata");
        userTest.setPhone("+375 29 692-82-42");
        userTest.setPassword("filimon");
        userTest.setSurname("test");
    }

    @Test
    public void userServiceTest() throws Exception {
        registeredUserTest();
    }

    @After
    public void tearDown() throws Exception {
        userDAO.delete(userTest);
    }

    public void registeredUserTest() throws Exception {
        password = userTest.getPassword();
        int registerSuccess = userService.registeredUser(userTest);
        Assert.assertEquals(registerSuccess, 1);
    }
}