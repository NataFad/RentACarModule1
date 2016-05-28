/**
 *
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.managers.CoderManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Class UserDAO
 * <p>
 * Class UserDAO creates object User and executes queries the table Users.
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
@Repository("userDAO")
public class UserDAO extends DAO<User> implements IUserDAO {

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

    @Override
    public User getUser(String login, String password) {
        User user = null;
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("login", login), Restrictions.eq("password", CoderManager.getHashCode(password))));
        /**
         String hql = "SELECT U FROM User as U "
         + "WHERE U.password = '" + CoderManager.getHashCode(password) + "' and U.login = :loginUser";
         Query query = session.createQuery(hql);
         query.setParameter("loginUser", login);
         */
        user = (User) criteria.uniqueResult();
        log.info("User: " + user);
        return user;
    }

    @Override
    public User getById(int id) {
        return getByKey("id", id);
    }

    @Override
    public boolean checkLogin(String login) {
        boolean checkResult = false;
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.eq("login", login));
        if (criteria.list().isEmpty()) {
            checkResult = true;
        }
        log.info("Results: " + checkResult);
        return checkResult;
    }

    @Override
    public int getAccess(String id) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT U.access FROM User as U "
                + "WHERE U.id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", Integer.parseInt(id));
        int access = 0;
        access = (int) query.uniqueResult();
        log.info("Access: " + access);
        return access;
    }
}