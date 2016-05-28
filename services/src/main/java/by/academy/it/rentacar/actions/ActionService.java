
package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.dao.ModelAndMarkDAO;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.ModelAndMark;
import by.academy.it.rentacar.enums.Transmission;

import java.util.ArrayList;

/**
 * Class Action makes requests to the DAO to create filter lists on the form
 * 
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 * 
 */
public class ActionService implements IActionService{

	private volatile static ActionService instance;
	private ModelAndMarkDAO modelAndMarkDAO;
	private ActionService(){}

	public static ActionService getInstance() {
		if (instance == null) {
			synchronized (ActionService.class) {
				if (instance == null) {
					instance = new ActionService();
				}
			}
		}
		return instance;
	}

	/**
	 * Method getListTransmission() gets list of transmissions
	 *
	 * @return ArrayList<Transmission>
     */
	public ArrayList<Transmission> getListTransmission(){
		// List of transmission
		ArrayList<Transmission> transList = new ArrayList<Transmission>();
		transList.add(Transmission.AUTO);
		transList.add(Transmission.MANUAL);
		return transList;
	}

	/**
	 * Method getListFuel() gets list of fuels
	 *
	 * @return ArrayList<Fuel>
	 */
	public ArrayList<Fuel> getListFuel(){
		// List of fuels
		ArrayList<Fuel> fuelsList = FuelService.getInstance().getListFuel();
		return fuelsList;
	}

	/**
	 * Method getListModel() gets list of models and marks
	 *
	 * @return ArrayList<ModelAndMark>
	 */
	public ArrayList<ModelAndMark> getListModel(){
		ArrayList<ModelAndMark> modelList = null;
		//try {
			modelList = (ArrayList<ModelAndMark>) modelAndMarkDAO.getAll();
//		} catch (DAOException e) {
//			e.printStackTrace();
//		}
		return modelList;
	}

	/**
	 * Method getListPerPage() gets list per page
	 *
	 * @return ArrayList<String>
	 */
	public ArrayList<String> getListPerPage(){
		// List per page
		ArrayList<String> perPageList = new ArrayList<String>();
		perPageList.add("3");
		perPageList.add("5");
		perPageList.add("10");
		return perPageList;
	}
}
