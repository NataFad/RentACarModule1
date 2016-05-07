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
import java.util.ArrayList;
import java.util.Date;

/**
 * Class GetAllCarsAction
 * 
 * Class GetAllCarsAction returns a list of all cars.
 * 
 * @author Fadeeva Natallia
 * @version 1.0
 * @since 2016-03
 *
 */
public class GetAllCarsAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// period
		request.setAttribute("fromDate", formateDate(new Date()));
		request.setAttribute("byDate", formateDate(new Date()));
		
		getListFilterCar(request, 0);
		
		ArrayList<Car> list = CarService.getInstance().getAllCars();
		if (list.isEmpty()) {
			list = null;
			request.setAttribute("search_result", list);
			request.getSession().setAttribute("errorSearchCarMessage", errorManager.getProperty("search.nullsearch"));
		}
		request.setAttribute("search_result", list);

		return ConfigurationManager.getProperty("page.search");
	}

}
