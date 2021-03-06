/**
 *
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.enums.Transmission;
import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 * Class PriceDAO
 * <p>
 * Class PriceDAO creates object Price and executes queries the table price.
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@Repository("priceDAO")
public class PriceDAO extends DAO<Price> implements IPriceDAO {

    private static Logger log = Logger.getLogger(PriceDAO.class);

    public PriceDAO() {
        super();
    }

    @Override
    public Price getByTransmissionAndFuel(Transmission transmission, Fuel fuel) {
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
        price = (Price) criteria.uniqueResult();
        log.info("Price: " + price);
        return price;
    }
}
