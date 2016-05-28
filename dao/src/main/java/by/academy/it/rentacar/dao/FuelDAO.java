package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Fuel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Class FuelDAO
 * <p>
 * Class FuelDAO creates object Fuel and executes queries the table Fuels.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@Repository
public class FuelDAO extends DAO<Fuel> implements IFuelDAO{

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

    @Override
    public List<Fuel> searchByName(String nameSearch){
        Session session = sessionFactory.getCurrentSession();
        String hql = "SELECT F FROM Fuel as F WHERE F.name = :nameFuel";
        Query query = session.createQuery(hql);
        query.setParameter("nameFuel", nameSearch);
        List<Fuel> list = (ArrayList<Fuel>) query.setCacheable(true).list();
        return list;
    }

    @Override
    public Fuel getById(int id){
        return getByKey("id", id);
    }
}
