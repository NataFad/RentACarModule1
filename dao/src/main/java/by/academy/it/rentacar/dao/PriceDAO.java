/**
 *
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.exceptions.DAOException;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 * Class PriceDAO
 * <p>
 * Class PriceDAO creates object Price and executes queries the table price.
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 */
public class PriceDAO extends DAO<Price> {

    private volatile static PriceDAO instance;
    private static Logger log = Logger.getLogger(PriceDAO.class);

    private PriceDAO() {
        super();
    }

    public static PriceDAO getInstance() {
        if (instance == null) {
            synchronized (PriceDAO.class) {
                if (instance == null) {
                    instance = new PriceDAO();
                }
            }
        }
        return instance;
    }

    /**
     * Method getByTransmissionAndFuel()
     * searches object price by transmission and fuel
     */
    public Price getByTransmissionAndFuel(Transmission transmission, Fuel fuel) throws DAOException {
        Price price = null;
        /**String hql = "SELECT P FROM Price as P "
         + "WHERE P.transmission = '"+ transmission.getValue() +"' and P.fuel = :fuel";
         Query query = session.createQuery(hql);
         query.setParameter("fuel", fuel);
         query.getQueryString();
         price = (Price) query.uniqueResult();
         */

        Criteria criteria = createEntityCriteria();
        criteria.add(Restrictions.and(Restrictions.eq("fuel", fuel), Restrictions.eq("transmission", transmission)));
        System.out.println(criteria.toString());
        try {
            price = (Price) criteria.uniqueResult();
        } catch (HibernateException e) {
            log.error("Error get by transmission and fuel in PriceDAO " + e);
            throw new DAOException(e.getMessage());
        }
        return price;
    }
}
