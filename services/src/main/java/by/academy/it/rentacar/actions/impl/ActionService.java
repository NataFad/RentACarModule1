
package by.academy.it.rentacar.actions.impl;

import by.academy.it.rentacar.actions.IActionService;
import by.academy.it.rentacar.actions.IFuelService;
import by.academy.it.rentacar.dao.IModelAndMarkDAO;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.ModelAndMark;
import by.academy.it.rentacar.enums.ElementsPerPage;
import by.academy.it.rentacar.enums.Transmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Class Action makes requests to the DAO to create filter lists on the form
 * 
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 * 
 */
@Service("actionService")
@Transactional(readOnly = true)
public class ActionService implements IActionService {

	@Autowired
	private IFuelService fuelService;
	@Autowired
	private IModelAndMarkDAO modelAndMarkDAO;

	/**
	 * Method getListTransmission() gets list of transmissions
	 *
	 * @return ArrayList<Transmission>
     */
	public ArrayList<Transmission> getListTransmission(){
		// List of transmission
		ArrayList<Transmission> transList = new ArrayList<Transmission>();
		for (Transmission transmissions : Transmission.values()){
			transList.add(transmissions);
		}
		return transList;
	}

	/**
	 * Method getListFuel() gets list of fuels
	 *
	 * @return ArrayList<Fuel>
	 */
	public ArrayList<Fuel> getListFuel(){
		// List of fuels
		ArrayList<Fuel> fuelsList = fuelService.getListFuel();
		return fuelsList;
	}

	/**
	 * Method getListModel() gets list of models and marks
	 *
	 * @return ArrayList<ModelAndMark>
	 */
	public ArrayList<ModelAndMark> getListModel(){
		ArrayList<ModelAndMark> modelList = null;
		modelList = (ArrayList<ModelAndMark>) modelAndMarkDAO.getAll();
		return modelList;
	}

	/**
	 * Method getListPerPage() gets list per page
	 *
	 * @return ArrayList<String>
	 */
	public ArrayList<ElementsPerPage> getListPerPage(){
		// List per page
		ArrayList<ElementsPerPage> perPageList = new ArrayList<ElementsPerPage>();
		for (ElementsPerPage elements : ElementsPerPage.values()){
			perPageList.add(elements);
		}
		return perPageList;
	}
}
