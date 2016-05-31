/**
 *
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.User;
import by.academy.it.rentacar.managers.CoderManager;
import by.academy.it.rentacar.viewobject.UserVO;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

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
    public UserVO getUser(String login, String password) {
        UserVO userVO = null;
        String sqlQuery = "SELECT UserVO.id AS id, " +
                "UserVO.access AS access, " +
                "UserVO.name AS firstname, " +
                "UserVO.surname AS surname, " +
                "UserVO.login AS login, " +
                "UserVO.password AS password," +
                "true " +
                "FROM users AS UserVO " +
                "WHERE UserVO.password = '" + CoderManager.getHashCode(password) + "' " +
                "and UserVO.login = '" + login + "'";
        log.debug("sqlQuery: " + sqlQuery);
        List<UserVO> userList = getSession().createSQLQuery(sqlQuery)
                .addScalar("id", StandardBasicTypes.INTEGER)
                .addScalar("access", StandardBasicTypes.INTEGER)
                .addScalar("firstname", StandardBasicTypes.STRING)
                .addScalar("surname", StandardBasicTypes.STRING)
                .addScalar("login", StandardBasicTypes.STRING)
                .addScalar("password", StandardBasicTypes.STRING)
                .setResultTransformer(Transformers.aliasToBean(UserVO.class)).list();
        if (!userList.isEmpty()){
            userVO = userList.get(0);
        }
        log.info("userVO: " + userVO);
        return userVO;
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