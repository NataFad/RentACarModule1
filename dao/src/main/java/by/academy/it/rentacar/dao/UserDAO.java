/**
 *
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.managers.CoderManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

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
        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("login", login), Restrictions.eq("password", CoderManager.getHashCode(password))));
        /**
         String hql = "SELECT U FROM User as U "
         + "WHERE U.password = '" + CoderManager.getHashCode(password) + "' and U.login = :loginUser";
         Query query = session.createQuery(hql);
         query.setParameter("loginUser", login);
         */
        List<User> userList = (ArrayList<User>) criteria.list();
        if (userList.isEmpty()){
            log.info("User not found");
            user = userList.get(0);
        }
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
        String hql = "SELECT U.access FROM User as U "
                + "WHERE U.id = :id";
        Query query = getSession().createQuery(hql);
        query.setParameter("id", Integer.parseInt(id));
        int access = 0;
        access = (int) query.uniqueResult();
        log.info("Access: " + access);
        return access;
    }
}