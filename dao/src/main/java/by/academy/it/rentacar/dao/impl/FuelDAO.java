package by.academy.it.rentacar.dao.impl;

import by.academy.it.rentacar.dao.IFuelDAO;
import by.academy.it.rentacar.entity.Fuel;
import org.apache.log4j.Logger;
import org.hibernate.Query;
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
@Repository("fuelDAO")
public class FuelDAO extends DAO<Fuel> implements IFuelDAO {

    private static Logger log = Logger.getLogger(FuelDAO.class);

    public FuelDAO() {
        super();
    }

    @Override
    public List<Fuel> searchByName(String nameSearch){
        String hql = "SELECT F FROM Fuel as F WHERE F.name = :nameFuel";
        Query query = getSession().createQuery(hql);
        query.setParameter("nameFuel", nameSearch);
        List<Fuel> list = null;
        list = (ArrayList<Fuel>) query.list();
        log.info("Results:" + list);
        return list;
    }

    @Override
    public Fuel getById(int id){
        return getByKey("id", id);
    }
}
