/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.by.academy.it.rentacar.util.HibernateUtil;
import by.academy.it.rentacar.exceptions.DAOException;
import by.academy.it.rentacar.managers.DBSqlManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
    protected static HibernateUtil util = HibernateUtil.getInstance();
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
     * @param t
     */
    public void saveOrUpdate(T t) throws DAOException{
        try {
            Session session = util.getSession();
            transaction = session.beginTransaction();
            session.saveOrUpdate(t);
            log.info("saveOrUpdate(t):" + t);
            transaction.commit();
            log.info("Save or update (commit):" + t);
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
     * @return t
     */
    public T get(Serializable id) throws DAOException {
        log.info("Get class by id:" + id);
        T t = null;
        try {
            Session session = util.getSession();
            transaction = session.beginTransaction();
            t = (T) session.get(getPersistentClass(), id);
            transaction.commit();
            log.info("get clazz:" + t);
        } catch (HibernateException e) {
            transaction.rollback();
            log.error("Error get " + getPersistentClass() + " in DAO " + e);
            throw new DAOException(e.getMessage());
        }
        return t;
    }

    /**
     * Method load() gets the object T by id from the table
     *
     * @param id
     * @return t
     */
    public T load(Serializable id) throws DAOException {
        log.info("Load class by id:" + id);
        T t = null;
        try {
            Session session = util.getSession();
            transaction = session.beginTransaction();
            t = (T) session.load(getPersistentClass(), id);
            log.info("load() clazz:" + t);
            session.isDirty();
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Error load() " + getPersistentClass() + " in DAO " + e);
            transaction.rollback();
            throw new DAOException(e.getMessage());
        }
        return t;
    }

    /**
     * Method delete() deletes object T from the table
     *
     * @param t
     */
    public void delete(T t) throws DAOException {
        try {
            Session session = util.getSession();
            transaction = session.beginTransaction();
            session.delete(t);
            transaction.commit();
            log.info("Delete:" + t);
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
            Session session = util.getSession();
            transaction = session.beginTransaction();
            results = (ArrayList) session.createCriteria(getPersistentClass()).list();
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
     * Method getPersistentClass() returns the name ot the parametrized type
     *
     * @return results
     */
    private Class getPersistentClass(){
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
}
