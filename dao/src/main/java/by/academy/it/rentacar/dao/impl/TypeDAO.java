/**
 * 
 */
package by.academy.it.rentacar.dao.impl;

import by.academy.it.rentacar.dao.ITypeDAO;
import by.academy.it.rentacar.dao.impl.DAO;
import by.academy.it.rentacar.entity.Type;
import org.springframework.stereotype.Repository;

/**
 * Class TypeDAO
 * 
 * Class TypeDAO creates object Type and executes queries the table types.
 * 
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 * 
 */
@Repository("typeDAO")
public class TypeDAO extends DAO<Type> implements ITypeDAO {

	public TypeDAO() {
		super();
	}

	@Override
	public Type getById(int id) {
		return getByKey("id", id);
	}
}
