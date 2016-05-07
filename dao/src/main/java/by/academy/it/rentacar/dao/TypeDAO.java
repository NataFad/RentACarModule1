/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Type;

/**
 * Class TypeDAO
 * 
 * Class TypeDAO creates object Type and executes queries the table types.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class TypeDAO extends DAO<Type> {

	private volatile static TypeDAO instance;

	private TypeDAO() {
		super();
	}

	public static TypeDAO getInstance() {
		if (instance == null) {
			synchronized (TypeDAO.class) {
				if (instance == null) {
					instance = new TypeDAO();
				}
			}
		}
		return instance;
	}

	/**
	 * Method getById() searches object type by id
	 *
	 */
	public Type getById(int id) {
		return getByKey("id", id);
	}
}
