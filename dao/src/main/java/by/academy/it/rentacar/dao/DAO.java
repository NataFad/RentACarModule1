/**
 *
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.exceptions.DAOException;
import by.academy.it.rentacar.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class DAO implements IDAO
 * contains: DBConnectionPool poolInstance; DBSqlManager sqlManager.
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 */
public abstract class DAO<T> implements IDAO<T> {

    private static Logger log = Logger.getLogger(DAO.class);

    /**
     * Method saveOrUpdate() saves or updates object T from the table
     *
     * @param entity
     */
    public void saveOrUpdate(T entity) throws DAOException {
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.saveOrUpdate(entity);
            log.info("saveOrUpdate(entity):" + entity);
        } catch (HibernateException e) {
            log.error("Error save or update " + getPersistentClass() + " in DAO " + e);
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * Method get() gets the object T by id from the table
     *
     * @param id
     * @return entity
     */
    public T get(Serializable id) throws DAOException {
        Session session = HibernateUtil.getInstance().getSession();
        log.info("Get class by id:" + id);
        T entity = null;
        try {
            entity = (T) session.get(getPersistentClass(), id);
            session.flush();
            log.info("get clazz:" + entity);
        } catch (HibernateException e) {
            log.error("Error get " + getPersistentClass() + " in DAO " + e);
            throw new DAOException(e.getMessage());
        }
        return entity;
    }

    /**
     * Method load() gets the object T by id from the table
     *
     * @param id
     * @return entity
     */
    public T load(Serializable id) throws DAOException {
        Session session = HibernateUtil.getInstance().getSession();
        log.info("Load class by id:" + id);
        T entity = null;
        try {
            entity = (T) session.load(getPersistentClass(), id);
            log.info("load() clazz:" + entity);
            session.isDirty();
        } catch (HibernateException e) {
            log.error("Error load() " + getPersistentClass() + " in DAO " + e);
            throw new DAOException(e.getMessage());
        }
        return entity;
    }

    /**
     * Method delete() deletes object T from the table
     *
     * @param entity
     */
    public void delete(T entity) throws DAOException {
        Session session = HibernateUtil.getInstance().getSession();
        try {
            session.delete(entity);
            log.info("Delete:" + entity);
        } catch (HibernateException e) {
            log.error("Error save or update " + getPersistentClass() + " in DAO " + e);
            throw new DAOException(e.getMessage());
        }
    }

    /**
     * Method getAll() gets all objects T from the table
     *
     * @return results
     */
    public List<T> getAll() throws DAOException {
        List<T> results = null;
        try {
            results = (ArrayList) createEntityCriteria().list();
            log.info("List:" + results);
        } catch (HibernateException e) {
            log.error("Error save or update " + getPersistentClass() + " in DAO " + e);
            throw new DAOException(e.getMessage());
        }
        return results;
    }

    /**
     * Method getByKey() searches by the value of the key
     */
    public T getByKey(String key, Serializable value) {
        return (T) createEntityCriteria().add(Restrictions.eq(key, value)).uniqueResult();
    }

    /**
     * Method count() gets count of entries in the table
     * <p>
     */
    public long count() {
        Long count = -1L;
        List results = createEntityCriteria().setProjection(Projections.rowCount()).list();
        count = (Long) results.get(0);
        return count;
    }

    /**
     * Method getPersistentClass() returns the name ot the parametrized type
     *
     * @return results
     */
    protected Criteria createEntityCriteria() {
        Session session = HibernateUtil.getInstance().getSession();
        return session.createCriteria(getPersistentClass());
    }

    /**
     * Method getPersistentClass() returns the name ot the parametrized type
     *
     * @return results
     */
    private Class getPersistentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
