/**
 * 
 */
package by.academy.it.rentacar.actions.car;

import by.academy.it.rentacar.actions.Action;
import by.academy.it.rentacar.actions.CarService;
import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.managers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * Class addCarAction
 * 
 * Class addCarAction creates an object of class Car on the data entered and
 * calculated its cost and discounted rent for the day.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class AddCarAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("addCar", 1);

		HashMap<String, String> parametresCar = new HashMap<String, String>();
		parametresCar.put("transmission", request.getParameter("transmission").trim());
		parametresCar.put("fuelId", request.getParameter("fuelId").trim());
		parametresCar.put("typeId", request.getParameter("typeId").trim());
		parametresCar.put("ratingId", request.getParameter("ratingId").trim());
		parametresCar.put("registrationNumber",	request.getParameter("registrationNumber").trim());
		parametresCar.put("modelId", request.getParameter("modelId").trim());
		parametresCar.put("description", request.getParameter("description").trim());

		Car car = CarService.getInstance().registeredCar(parametresCar);
		if (car == null){
			request.getSession().setAttribute("addCarMessage",  errorManager.getProperty("error.addcar"));
		} else if (car.getId() == 0) {
			request.getSession().setAttribute("addCarMessage",  errorManager.getProperty("error.addcar"));
		} else {
			request.getSession().setAttribute("addCarMessage",
					errorManager.getProperty("success.addcar") + " " + car.toString());
		}
		return ConfigurationManager.getProperty("page.addCar");
	}

}
