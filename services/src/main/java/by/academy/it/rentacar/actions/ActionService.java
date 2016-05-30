
package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.dao.IModelAndMarkDAO;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.ModelAndMark;
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
public class ActionService implements IActionService{

	@Autowired
	private IModelAndMarkDAO modelAndMarkDAO;
	@Autowired
	private IFuelService fuelService;

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
	public ArrayList<String> getListPerPage(){
		// List per page
		ArrayList<String> perPageList = new ArrayList<String>();
		perPageList.add("3");
		perPageList.add("5");
		perPageList.add("10");
		return perPageList;
	}
}
