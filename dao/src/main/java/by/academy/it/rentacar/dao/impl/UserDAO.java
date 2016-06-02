/**
 *
 */
package by.academy.it.rentacar.dao.impl;

import by.academy.it.rentacar.dao.IUserDAO;
import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.managers.CoderManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Class UserDAO
 * <p>
 * Class UserDAO creates object User and executes queries the table Users.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@Repository("userDAO")
public class UserDAO extends DAO<User> implements IUserDAO {

    private static Logger log = Logger.getLogger(UserDAO.class);

    public UserDAO() {
        super();
    }

    @Override
    public User getUser(String login, String password) {
        User user = null;
        String hql = "SELECT U FROM User as U "
                + "WHERE U.login = :login and U.password = '" + CoderManager.getHashCode(password) + "'";
        Query query = getSession().createQuery(hql);
        query.setParameter("login", login);
        query.getQueryString();
        user = (User) query.uniqueResult();

        log.info("userVO: " + user);
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
        String hql = "SELECT U.access FROM User as U "
                + "WHERE U.id = :id";
        Query query = getSession().createQuery(hql);
        query.setParameter("id", Integer.parseInt(id));
        int access = (int) query.uniqueResult();
        log.info("Access: " + access);
        return access;
    }
}