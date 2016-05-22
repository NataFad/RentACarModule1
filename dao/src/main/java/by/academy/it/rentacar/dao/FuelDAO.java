package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.exceptions.DAOException;
import by.academy.it.rentacar.util.HibernateUtil;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Class FuelDAO
 * <p>
 * Class FuelDAO creates object Fuel and executes queries the table Fuels.
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 */
public class FuelDAO extends DAO<Fuel>{

    private volatile static FuelDAO instance;
    private static Logger log = Logger.getLogger(FuelDAO.class);

    private FuelDAO() {
        super();
    }

    public static FuelDAO getInstance() {
        if (instance == null) {
            synchronized (FuelDAO.class) {
                if (instance == null) {
                    instance = new FuelDAO();
                }
            }
        }
        return instance;
    }

    /**
     * Method searchByName() searches all fuels by name
     *
     */
    public List<Fuel> searchByName(String nameSearch) throws DAOException {
        Session session = HibernateUtil.getInstance().getSession();
        String hql = "SELECT F FROM Fuel as F WHERE F.name = :nameFuel";
        Query query = session.createQuery(hql);
        query.setParameter("nameFuel", nameSearch);
        List<Fuel> list = null;
        try{
            list = (ArrayList<Fuel>) query.list();
        } catch (HibernateException e){
            log.error("Error of the search of fuel by name in FuelDAO " + e);
            throw new DAOException(e.getMessage());
        }
        return list;
    }

    /**
     * Method getById() searches object fuel by id
     *
     */
    public Fuel getById(int id){
        return getByKey("id", id);
    }
}
