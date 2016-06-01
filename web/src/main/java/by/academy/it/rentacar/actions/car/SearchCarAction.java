/**
 * 
 */
package by.academy.it.rentacar.actions.car;

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
//public class SearchCarAction extends Action {
//
//	private CarDAO carDAO;
//
//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) {
//		// period
//		Date fromDate = Date.valueOf(request.getParameter("fromDate").trim());
//		Date byDate = Date.valueOf(request.getParameter("byDate").trim());
//		HashMap<String, String> filterValues = new HashMap<String, String>();
//		// Transmission
//		String transmission = request.getParameter("transmission").trim();
//		if (!transmission.equals("")) {
//			filterValues.put("transmission", transmission);
//			request.setAttribute("transmission", transmission);
//		}
//		// Fuels
//		String fuelId = request.getParameter("fuelId").trim();
//		if (!fuelId.equals("0")) {
//			filterValues.put("fuelId", fuelId);
//			request.setAttribute("fuelId", Integer.parseInt(fuelId));
//		}
//
//		// Type
//		String typeId = request.getParameter("typeId").trim();
//		if (!typeId.equals("0")) {
//			filterValues.put("typeId", typeId);
//			request.setAttribute("typeId", Integer.parseInt(typeId));
//		}
//
//		// Rating
//		String ratingId = request.getParameter("ratingId");
//		if (!ratingId.equals("0")) {
//			filterValues.put("ratingId", ratingId);
//			request.setAttribute("ratingId", Integer.parseInt(ratingId));
//		}
//
//		// recordsPerPage
//		String recordPerPage = request.getParameter("recordPerPage");
//		filterValues.put("recordsPerPage", recordPerPage);
//		request.setAttribute("recordPerPage", Integer.parseInt(recordPerPage));
//		filterValues.put("page", "1");
//		request.setAttribute("page", 1);
//
//		// period
//		request.setAttribute("fromDate", formateDate(fromDate));
//		request.setAttribute("byDate", formateDate(byDate));
//		getListFilterCar(request, 0);
//
//		BigInteger count;
//		//try {
//			count = carDAO.countCarByFilter(fromDate, byDate, filterValues);
//			request.getSession().setAttribute("errorFilterCarMassager", "Count by filter " + count);
////		} catch (DAOException e) {
////			request.getSession().setAttribute("errorFilterCarMassager", "Null count");
////		}
//
//		HashMap<String, Object> filterResponse = new HashMap<String, Object>();
//		List<CarVO> list = CarService.getInstance().getSearchCar(fromDate, byDate, filterValues);
//		if (list.isEmpty()) {
//			list = null;
//			request.setAttribute("search_result", list);
//			request.getSession().setAttribute("errorSearchCarMessage", errorManager.getProperty("search.nullsearch"));
//		}
//		request.setAttribute("search_result", list);
//
//		return ConfigurationManager.getProperty("page.search");
//	}
//
//}
