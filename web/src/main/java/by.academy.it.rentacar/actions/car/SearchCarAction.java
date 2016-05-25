/**
 * 
 */
package by.academy.it.rentacar.actions.car;

import by.academy.it.rentacar.actions.Action;
import by.academy.it.rentacar.actions.CarService;
import by.academy.it.rentacar.dao.CarDAO;
import by.academy.it.rentacar.exceptions.DAOException;
import by.academy.it.rentacar.managers.ConfigurationManager;
import by.academy.it.rentacar.viewobject.CarViewObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;

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
		if (!transmission.equals("")) {
			filterValues.put("transmission", transmission);
		}
		// Fuels
		String fuelId = request.getParameter("fuelId").trim();
		if (!fuelId.equals("")) {
			filterValues.put("fuelId", fuelId);
		}

		// Type
		String typeId = request.getParameter("typeId").trim();
		if (!typeId.equals("")) {
			filterValues.put("typeId", typeId);
		}

		// Rating
		String ratingId = request.getParameter("ratingId");
		if (!ratingId.equals("")) {
			filterValues.put("ratingId", ratingId);
		}

		// recordsPerPage
		String recordPerPage = request.getParameter("recordPerPage");
		filterValues.put("recordsPerPage", recordPerPage);
		filterValues.put("page", "1");

		// period
		request.setAttribute("fromDate", formateDate(fromDate));
		request.setAttribute("byDate", formateDate(byDate));
		getListFilterCar(request, 0);

		BigInteger count;
		try {
			count = CarDAO.getInstance().countCarByFilter(fromDate, byDate, filterValues);
			request.getSession().setAttribute("errorFilterCarMassager", "Count by filter " + count);
		} catch (DAOException e) {
			request.getSession().setAttribute("errorFilterCarMassager", "Null count");
		}

		HashMap<String, Object> filterResponse = new HashMap<String, Object>();
		List<CarViewObject> list = CarService.getInstance().getSearchCar(fromDate, byDate, filterValues, filterResponse);
		if (list.isEmpty()) {
			list = null;
			request.setAttribute("search_result", list);
			request.getSession().setAttribute("errorSearchCarMessage", errorManager.getProperty("search.nullsearch"));
		}
		request.setAttribute("search_result", list);

		for (String key: filterResponse.keySet()) {
			request.setAttribute(key, filterResponse.get(key));
		}
		return ConfigurationManager.getProperty("page.search");
	}

}
