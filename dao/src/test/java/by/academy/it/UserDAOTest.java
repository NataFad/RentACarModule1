package by.academy.it;

import by.academy.it.rentacar.by.academy.it.rentacar.util.HibernateUtil;
import by.academy.it.rentacar.dao.UserDAO;
import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.enums.TypeUser;
import by.academy.it.rentacar.exceptions.DAOException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Nata on 13.05.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 */
public class UserDAOTest {

    private static UserDAO userDAO = UserDAO.getInstance();
    private static User userTest;

    @Before
    public void setUp() throws Exception {
        Calendar calendar = new GregorianCalendar(1975, 0, 25);
        userTest = new User();
        userTest.setName("test");
        userTest.setAccess(1);
        userTest.setBirthday(calendar.getTime());
        userTest.setEmail("nata.filimon@gmail.com");
        userTest.setLogin("testNata");
        userTest.setPhone("+375 29 692-82-42");
        userTest.setPassword("filimon");
        userTest.setSurname("test");
    }

    @After
    public void tearDown() throws Exception {
        HibernateUtil.getInstance().closeSession();
    }

    @Test
    public void userDAOTest() throws Exception {
        checkLoginTest();
        addUser();
        getByIdTest();
        getAccessTest(TypeUser.USER.getType());
        updateAccessTest();
        deleteTest();
    }

    private void addUser(){
        try {
            userDAO.saveOrUpdate(userTest);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    private void getByIdTest() throws Exception {
        User userExpexted = userDAO.getById(userTest.getId());
        Assert.assertNotNull(userExpexted);
        Assert.assertEquals(userExpexted, userTest);
    }

    private void checkLoginTest() throws Exception {
        boolean check = userDAO.checkLogin("testfil");
        Assert.assertTrue(check);
    }

    private void getAccessTest(int accessExpected) throws Exception {
        int access = userDAO.getAccess(Integer.toString(userTest.getId()));
        Assert.assertEquals(access, accessExpected);
    }

    private void updateAccessTest() throws Exception {
        userTest.setAccess(TypeUser.ADMINISTRATOR.getType());
        userDAO.saveOrUpdate(userTest);
        getAccessTest(TypeUser.ADMINISTRATOR.getType());
    }

    private void deleteTest() throws DAOException {
        try {
            userDAO.delete(userTest);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}