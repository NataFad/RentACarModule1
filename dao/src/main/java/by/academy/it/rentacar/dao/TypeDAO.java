/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.Type;
import org.springframework.stereotype.Repository;

/**
 * Class TypeDAO
 * 
 * Class TypeDAO creates object Type and executes queries the table types.
 * 
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 * 
 */
@Repository("typeDAO")
public class TypeDAO extends DAO<Type> implements ITypeDAO{

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

	@Override
	public Type getById(int id) {
		return getByKey("id", id);
	}
}
