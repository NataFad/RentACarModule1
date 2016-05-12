package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Fuel;
import org.hibernate.Query;

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
    public List<Fuel> searchByName(String nameSearch) {
        String hql = "SELECT F FROM Fuel as F WHERE F.name = :nameFuel";
        Query query = session.createQuery(hql);
        query.setParameter("nameFuel", nameSearch);
        List<Fuel> list = (ArrayList<Fuel>) query.list();
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
