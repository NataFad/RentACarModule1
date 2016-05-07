/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.by.academy.it.rentacar.util.HibernateUtil;
import by.academy.it.rentacar.exceptions.DAOException;
import by.academy.it.rentacar.managers.DBSqlManager;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**Abstract class DAO implements IDAO
 * contains: DBConnectionPool poolInstance; DBSqlManager sqlManager.
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 *
 */
public abstract class DAO<T> implements IDAO<T> {
    protected static DBSqlManager sqlManager;
    
    protected DAO() {
        sqlManager = DBSqlManager.getInstance();
    }

    private static Logger log = Logger.getLogger(DAO.class);
    protected static Session session = HibernateUtil.getInstance().getSession();
    protected Transaction transaction = null;

    /**
     * Method getCurrentSQLDate()
     * translate the format util of the date in the format sql
     *
     * @param date
     * @return
     */
    protected static java.sql.Date getCurrentSQLDate(java.util.Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

    /**
     * Method getCurrentDate()
     * translate the format sql of the date in the format util
     *
     * @param sqlDate
     * @return
     */
    protected static java.util.Date getCurrentDate(java.sql.Date sqlDate) {
		java.util.Date date = new java.util.Date(sqlDate.getTime());
		return date;
	}

    /**
     * Method saveOrUpdate() saves or updates object T from the table
     *
     * @param entity
     */
    public void saveOrUpdate(T entity) throws DAOException{
        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(entity);
            log.info("saveOrUpdate(entity):" + entity);
            session.flush();
            transaction.commit();
            log.info("Save or update (commit):" + entity);
        } catch (HibernateException e) {
            log.error("Error save or update " + getPersistentClass() + " in DAO " + e);
            transaction.rollback();
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
        log.info("Get class by id:" + id);
        T entity = null;
        try {
            transaction = session.beginTransaction();
            entity = (T) session.get(getPersistentClass(), id);
            session.flush();
            transaction.commit();
            log.info("get clazz:" + entity);
        } catch (HibernateException e) {
            transaction.rollback();
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
        log.info("Load class by id:" + id);
        T entity = null;
        try {
            transaction = session.beginTransaction();
            entity = (T) session.load(getPersistentClass(), id);
            log.info("load() clazz:" + entity);
            session.isDirty();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error load() " + getPersistentClass() + " in DAO " + e);
            transaction.rollback();
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
        try {
            transaction = session.beginTransaction();
            session.delete(entity);
            session.flush();
            transaction.commit();
            log.info("Delete:" + entity);
        } catch (HibernateException e) {
            log.error("Error save or update " + getPersistentClass() + " in DAO " + e);
            transaction.rollback();
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
            transaction = session.beginTransaction();
            results = (ArrayList) createEntityCriteria().list();
            transaction.commit();
            log.info("List:" + results);
        } catch (HibernateException e) {
            log.error("Error save or update " + getPersistentClass() + " in DAO " + e);
            transaction.rollback();
            throw new DAOException(e.getMessage());
        }
        return results;
    }

    /**
     * Method getByKey() searches by the value of the key
     *
     */
    public T getByKey(String key, Serializable value){
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
    protected Criteria createEntityCriteria(){
        return session.createCriteria(getPersistentClass());
    }

    /**
     * Method getPersistentClass() returns the name ot the parametrized type
     *
     * @return results
     */
    private Class getPersistentClass(){
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
