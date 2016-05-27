/**
 *
 */
package by.academy.it.rentacar.actions.car;

import by.academy.it.rentacar.actions.Action;
import by.academy.it.rentacar.actions.CarService;
import by.academy.it.rentacar.managers.ConfigurationManager;
import by.academy.it.rentacar.viewobject.CarViewObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Class GetAllCarsAction
 * <p>
 * Class GetAllCarsAction returns a list of all cars.
 *
 * @author Fadeeva Natallia
 * @version 1.0
 * @since 2016-03
 */
public class GetAllCarsAction extends Action {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        // period
        java.sql.Date fromDate = java.sql.Date.valueOf("2016-01-01");
        request.setAttribute("fromDate", formateDate(new Date()));
        request.setAttribute("byDate", formateDate(new Date()));

        getListFilterCar(request, 0);

        int page = 1;
        int recordsPerPage = 10;
        HashMap<String, String> filterValues = new HashMap<String, String>();
        filterValues.put("page", Integer.toString(page));
        filterValues.put("recordsPerPage", Integer.toString(recordsPerPage));

        List<CarViewObject> list = CarService.getInstance().getSearchCar(fromDate, fromDate, filterValues);
        if (list.isEmpty()) {
            list = null;
            request.setAttribute("search_result", list);
            request.getSession().setAttribute("errorSearchCarMessage", errorManager.getProperty("search.nullsearch"));
        }
        request.setAttribute("search_result", list);
        return ConfigurationManager.getProperty("page.search");
    }

}
