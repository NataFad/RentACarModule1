
package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.beans.Fuel;
import by.academy.it.rentacar.beans.ModelAndMark;
import by.academy.it.rentacar.dao.FuelDAO;
import by.academy.it.rentacar.dao.ModelAndMarkDAO;
import by.academy.it.rentacar.enums.Transmission;

import java.util.ArrayList;

/**
 * Class Action makes requests to the DAO to create filter lists on the form
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class ActionService {

	private volatile static ActionService instance;

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
		ArrayList<Fuel> fuelsList = new ArrayList<Fuel>();
		fuelsList = FuelDAO.getInstance().getAll();
		return fuelsList;
	}

	/**
	 * Method getListModel() gets list of models and marks
	 *
	 * @return ArrayList<ModelAndMark>
	 */
	public ArrayList<ModelAndMark> getListModel(){
		ArrayList<ModelAndMark> modelList = new ArrayList<ModelAndMark>();
		modelList = ModelAndMarkDAO.getInstance().getAll();
		return modelList;
	}
}