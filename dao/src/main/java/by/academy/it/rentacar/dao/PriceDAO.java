/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.enums.Transmission;
import org.hibernate.Query;

/**
 * Class PriceDAO
 * 
 * Class PriceDAO creates object Price and executes queries the table price.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class PriceDAO extends DAO<Price> {

	private volatile static PriceDAO instance;

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
	 * Method getByTransmissionAndFuel() searches object price by transmission and fuel
	 *
	 */
	public Price getByTransmissionAndFuel(Transmission transmission, Fuel fuel) {
		Price price = null;
		String hql = "SELECT P FROM Price as P "
		 	+ "WHERE P.transmission = '"+ transmission.name() +"' and P.fuel = :fuel";
		Query query = session.createQuery(hql);
		query.setParameter("fuel", fuel);
		query.getQueryString();
		price = (Price) query.uniqueResult();


		/**Criteria criteria = createEntityCriteria();
		criteria.add(Restrictions.and(Restrictions.eq("fuel",fuel), Restrictions.eq("transmission", transmission)));
		price = (Price) criteria.uniqueResult();
		*/
		return price;
	}
}
