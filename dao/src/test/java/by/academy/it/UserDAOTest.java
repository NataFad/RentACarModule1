package by.academy.it;

import by.academy.it.rentacar.dao.IUserDAO;
import by.academy.it.rentacar.entity.UserEntity;
import by.academy.it.rentacar.enums.TypeUser;
import by.academy.it.rentacar.exceptions.DAOException;
import by.academy.it.rentacar.managers.CoderManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Nata on 13.05.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/testDaoContext.xml")
@Transactional
public class UserDAOTest {

    @Autowired
    private IUserDAO userDAO;
    private static UserEntity userTest;

    @Before
    public void setUp() throws Exception {
        Calendar calendar = new GregorianCalendar(1975, 0, 25);
        userTest = new UserEntity();
        userTest.setName("test");
        userTest.setAccess(1);
        userTest.setBirthday(calendar.getTime());
        userTest.setEmail("nata.filimon@gmail.com");
        userTest.setLogin("testNata");
        userTest.setPhone("+375 29 692-82-42");
        userTest.setPassword("filimon");
        userTest.setSurname("test");
    }

    @Test
    public void userDAOTest() throws Exception {
        checkLoginTest();
        addUser();
        getByIdTest();
        getAccessTest(TypeUser.USER.getValue());
        updateAccessTest();
        deleteTest();
    }

    private void addUser() throws Exception {
        String password = userTest.getPassword();
        userTest.setPassword(CoderManager.getHashCode(password));
        userDAO.saveOrUpdate(userTest);
        userTest.setPassword(password);
    }

    private void getByIdTest() throws Exception {
        UserEntity userExpected = userDAO.getById(userTest.getId());
        Assert.assertNotNull(userExpected);
        Assert.assertEquals(userExpected, userTest);
    }

    private void checkLoginTest() throws Exception {
        boolean check = userDAO.checkLogin(userTest.getLogin());
        Assert.assertTrue(check);
    }

    private void getAccessTest(int accessExpected) throws Exception {
        int access = userDAO.getAccess(Integer.toString(userTest.getId()));
        Assert.assertEquals(access, accessExpected);
    }

    private void updateAccessTest() throws Exception {
        userTest.setAccess(TypeUser.ADMINISTRATOR.getValue());
        userDAO.saveOrUpdate(userTest);
        getAccessTest(TypeUser.ADMINISTRATOR.getValue());
    }

    private void deleteTest() throws DAOException {
        userDAO.delete(userTest);
    }
}