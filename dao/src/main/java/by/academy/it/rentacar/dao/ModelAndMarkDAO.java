/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.ModelAndMark;
import org.springframework.stereotype.Repository;

/**
 * Class ModelAndMarkDAO
 * 
 * Class ModelAndMarkDAO creates object ModelAndMarkDAO and executes queries the
 * table ModelsAndMarks.
 * 
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 * 
 */
@Repository("modelAndMarkDAO")
public class ModelAndMarkDAO extends DAO<ModelAndMark> implements IModelAndMarkDAO{

	public ModelAndMarkDAO() {
		super();
	}

	@Override
	public ModelAndMark getById(int id){
		return getByKey("id", id);
	}
}
