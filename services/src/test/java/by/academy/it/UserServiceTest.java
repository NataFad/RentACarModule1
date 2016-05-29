package by.academy.it;

import by.academy.it.rentacar.actions.UserService;
import by.academy.it.rentacar.dao.UserDAO;
import by.academy.it.rentacar.entity.User;
import org.hibernate.Transaction;
import org.junit.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by Nata on 21.05.2016.
 */
@Ignore
public class UserServiceTest {

    private UserService userService = UserService.getInstance();
    private static UserDAO userDAO;
    private static User userTest;
    private String password;

    @Before
    public void setUp() throws Exception {
        Calendar calendar = new GregorianCalendar(1971, 1, 21);
        userTest = new User();
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
        loginUserTest();
        exitUserTest();
    }

    @After
    public void tearDown() throws Exception {
        Transaction transaction = userDAO.getSession().getTransaction();
        if (!transaction.isActive()){
            transaction.begin();
        }
        userDAO.delete(userTest);
        if (!transaction.wasCommitted()) {
            transaction.commit();
        }
    }

    public void registeredUserTest() throws Exception {
        Transaction transaction = userDAO.getSession().getTransaction();
        if (!transaction.isActive()){
            transaction.begin();
        }
        password = userTest.getPassword();
        int registerSuccess = userService.registeredUser(userTest);

        Assert.assertEquals(registerSuccess, 1);
    }

    public void loginUserTest() throws Exception {
        Transaction transaction = userDAO.getSession().getTransaction();
        if (!transaction.isActive()){
            transaction.begin();
        }
        User userReg = UserService.getInstance().loginUser(userTest.getLogin(), password, userTest);

        Assert.assertNotNull(userReg);
        Assert.assertEquals(userTest, userReg);
    }

    public void exitUserTest() throws Exception {
        User userGuest = UserService.getInstance().exitUser();

        Assert.assertNotNull(userGuest);
        Assert.assertEquals("User guest, name:", userGuest.getName(), "Гость");
        Assert.assertEquals("User guest, access:", userGuest.getAccess(), 0);
    }
}