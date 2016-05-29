/**
 *
 */
package by.academy.it.rentacar.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class DAO implements IDAO
 * contains: DBConnectionPool poolInstance; DBSqlManager sqlManager.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@Repository("DAO")
public class DAO<T> implements IDAO<T> {

    private static Logger log = Logger.getLogger(DAO.class);

    protected SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public DAO(){
    }

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public void saveOrUpdate(T entity) {
        getSession().saveOrUpdate(entity);
        log.info("saveOrUpdate(entity):" + entity);
    }

    @Override
    public T get(Serializable id) {
        Session session = getSession();
        log.info("Get class by id:" + id);
        T entity = null;
        entity = (T) session.get(getPersistentClass(), id);
        session.flush();
        log.info("get clazz:" + entity);
        return entity;
    }

    @Override
    public T load(Serializable id) {
        Session session = getSession();
        log.info("Load class by id:" + id);
        T entity = null;
        entity = (T) session.load(getPersistentClass(), id);
        log.info("load() clazz:" + entity);
        session.isDirty();
        return entity;
    }

    @Override
    public void delete(T entity) {
        getSession().delete(entity);
        log.info("Delete:" + entity);
    }

    @Override
    public List<T> getAll() {
        List<T> results = null;
        results = (ArrayList) createEntityCriteria().list();
        log.info("List:" + results);
        return results;
    }

    @Override
    public T getByKey(String key, Serializable value) {
        return (T) createEntityCriteria().add(Restrictions.eq(key, value)).uniqueResult();
    }

    @Override
    public long count() {
        Long count = -1L;
        List results = createEntityCriteria().setProjection(Projections.rowCount()).list();
        count = (Long) results.get(0);
        log.info("count: " + count);
        return count;
    }

    /**
     * Method getPersistentClass() returns the name ot the parametrized type
     *
     * @return results
     */
    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(getPersistentClass());
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
