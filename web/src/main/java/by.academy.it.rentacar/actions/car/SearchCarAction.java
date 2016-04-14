/**
 * 
 */
package by.academy.it.rentacar.actions.car;

import by.academy.it.rentacar.actions.Action;
import by.academy.it.rentacar.actions.CarService;
import by.academy.it.rentacar.beans.Car;
import by.academy.it.rentacar.managers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class SearchCarAction
 * 
 * Class SearchCarAction returns a list of cars by filters.
 * 
 * @author Fadeeva Natallia
 * @version 1.0
 * @since 2016-03
 *
 */
public class SearchCarAction extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		// period
		Date fromDate = Date.valueOf(request.getParameter("fromDate").trim());
		Date byDate = Date.valueOf(request.getParameter("byDate").trim());
		HashMap<String, String> filterValues = new HashMap<String, String>();
		// Transmission
		String transmission = request.getParameter("transmission").trim();
		if (!transmission.equals("0")) {
			filterValues.put("transmission", transmission);
		}
		// Fuels
		String fuelId = request.getParameter("fuelId").trim();
		if (!fuelId.equals("0")) {
			filterValues.put("fuelId", fuelId);
		}
		// Type
		String typeId = request.getParameter("typeId").trim();
		if (!typeId.equals("0")) {
			filterValues.put("typeId", typeId);
		}
		// Rating
		String ratingId = request.getParameter("ratingId");
		if (!ratingId.equals("0")) {
			filterValues.put("ratingId", ratingId);
		}

		// period
		request.setAttribute("fromDate", formateDate(fromDate));
		request.setAttribute("byDate", formateDate(byDate));
		getListFilterCar(request, 0);

		ArrayList<Car> list = CarService.getInstance().getSearchCar(fromDate, byDate, filterValues);
		if (list.isEmpty()) {
			list = null;
			request.setAttribute("search_result", list);
			request.getSession().setAttribute("errorSearchCarMessage", errorManager.getProperty("search.nullsearch"));
		}
		request.setAttribute("search_result", list);
		return ConfigurationManager.getProperty("page.search");
	}

}
