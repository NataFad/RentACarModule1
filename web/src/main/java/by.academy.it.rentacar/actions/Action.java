
package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.ModelAndMark;
import by.academy.it.rentacar.entity.Rating;
import by.academy.it.rentacar.entity.Type;
import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.managers.ErrorManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Abstract class Action
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public abstract class Action {

	protected static ErrorManager errorManager;

	protected Action() {
		errorManager = ErrorManager.getInstance();
	}

	/**
	 * Method formateDate() transforms date into "yyyy-MM-dd"
	 *
	 * @param toDate
	 * @return string
	 */
	public String formateDate(java.util.Date toDate) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(toDate);
	}

	/**
	 * Method getListFilterCar() generates lists of filters for search of car
	 *
	 * @param request
	 * @param getModels
     */
	public void getListFilterCar(HttpServletRequest request, int getModels) {
		// List of transmission
		ArrayList<Transmission> transList = ActionService.getInstance().getListTransmission();
		request.setAttribute("transList", transList);
		// List of fuels
		ArrayList<Fuel> fuelsList = ActionService.getInstance().getListFuel();
		if (fuelsList.isEmpty()){
			request.getSession().setAttribute("errorFilterCarMassager", errorManager.getProperty("search.error"));
		}
		request.setAttribute("fuelsList", fuelsList);
		// List of types
		ArrayList<Type> typeList = TypeService.getInstance().getListType();
		if (typeList.isEmpty()){
		 	request.getSession().setAttribute("errorFilterCarMassager", errorManager.getProperty("search.error"));
		}
		request.setAttribute("typeList", typeList);
		// List of ratings
		ArrayList<Rating> ratingList = RatingService.getInstance().getListRating();
		if (ratingList.isEmpty()){
			request.getSession().setAttribute("errorFilterCarMassager", errorManager.getProperty("search.error"));
		}
		request.setAttribute("ratingList", ratingList);
		// List of models
		if (getModels == 1) {
			ArrayList<ModelAndMark> modelList = ActionService.getInstance().getListModel();
			if (modelList.isEmpty()){
				request.getSession().setAttribute("errorFilterCarMassager", errorManager.getProperty("search.error"));
			}
			request.setAttribute("modelList", modelList);
		}
	}

	public abstract String execute(HttpServletRequest request, HttpServletResponse response);
}
