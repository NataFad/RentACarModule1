/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.entity.ModelAndMark;

/**
 * Class ModelAndMarkDAO
 * 
 * Class ModelAndMarkDAO creates object ModelAndMarkDAO and executes queries the
 * table ModelsAndMarks.
 * 
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 * 
 */
public class ModelAndMarkDAO extends DAO<ModelAndMark>{

	private volatile static ModelAndMarkDAO instance;

	private ModelAndMarkDAO() {
		super();
	}

	public static ModelAndMarkDAO getInstance() {
		if (instance == null) {
			synchronized (ModelAndMarkDAO.class) {
				if (instance == null) {
					instance = new ModelAndMarkDAO();
				}
			}
		}
		return instance;
	}

	/**
	 * Method getById() searches object model and mark by id
	 *
	 */
	public ModelAndMark getById(int id){
		return getByKey("id", id);
	}
}
