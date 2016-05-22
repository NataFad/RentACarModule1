/**
 *
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.exceptions.DAOException;
import by.academy.it.rentacar.managers.CoderManager;
import by.academy.it.rentacar.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
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
    private static Logger log = Logger.getLogger(UserDAO.class);

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
     */
    public User getUser(String login, String password) throws DAOException {
        Session session = HibernateUtil.getInstance().getSession();
        User user = null;
        /**
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("login", login), Restrictions.eq("password", CoderManager.getHashCode(password))));
        criteria.toString();
        */

        String hql = "SELECT U FROM User as U "
                + "WHERE U.password = '" + CoderManager.getHashCode(password) + "' and U.login = :loginUser";
        Query query = session.createQuery(hql);
        query.setParameter("loginUser", login);

         try {
           // user = (User) criteria.uniqueResult();
             user = (User) query.uniqueResult();
        } catch (HibernateException e) {
            log.error("Error get the user in UserDAO " + e);
            throw new DAOException(e.getMessage());
        }
        return user;
    }

    /**
     * Method getById() searches object user by id
     */
    public User getById(int id) {
        return getByKey("id", id);
    }

    /**
     * Method checkLogin() checks for entry to the entered login
     */
    public boolean checkLogin(String login) throws DAOException {
        boolean checkResult = false;

        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));

        try {
            if (criteria.list().isEmpty()) {
                checkResult = true;
            }
        } catch (HibernateException e) {
            log.error("Error check the login in UserDAO " + e);
            throw new DAOException(e.getMessage());
        }
        return checkResult;
    }

    /**
     * Method getAccess() gets the type of user access
     */
    public int getAccess(String id) throws DAOException {
        Session session = HibernateUtil.getInstance().getSession();
        String hql = "SELECT U.access FROM User as U "
                + "WHERE U.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", Integer.parseInt(id));
        query.toString();
        int access = 0;

        try {
            access = (int) query.uniqueResult();
        } catch (HibernateException e) {
            log.error("Error get access in UserDAO " + e);
            throw new DAOException(e.getMessage());
        }

        return access;
    }
}