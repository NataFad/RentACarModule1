/**
 *
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.managers.CoderManager;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;

/**
 * Class UserDAO
 * <p>
 * Class UserDAO creates object User and executes queries the table Users.
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
public class UserDAO extends DAO<User> {

    private volatile static UserDAO instance;

    private UserDAO() {
        super();
    }

    public static UserDAO getInstance() {
        if (instance == null) {
            synchronized (UserDAO.class) {
                if (instance == null) {
                    instance = new UserDAO();
                }
            }
        }
        return instance;
    }

    /**
     * Method getUser() searches user by login and password
     *
     */
    public User getUser(String login, String password) {
        User user = null;

        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("login", login), Restrictions.eq("password", CoderManager.getHashCode(password))));
        user = (User) criteria.uniqueResult();

        return user;
    }

    /**
     * Method getById() searches object user by id
     *
     */
    public User getById(int id) {
        return getByKey("id", id);
    }

    /**
     * Method checkLogin() checks for entry to the entered login
     *
     */
    public boolean checkLogin(String login) {
        boolean checkResult = false;

        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));

        if (criteria.list().isEmpty()) {
            checkResult = true;
        }
        return checkResult;
    }

    /**
     * Method getAccess() gets the type of user access
     *
     */
    public int getAccess(String id) {
        String hql = "SELECT U.access FROM User as U "
                + "WHERE U.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", Integer.parseInt(id));
        int access = (int) query.uniqueResult();

        return access;
    }
}