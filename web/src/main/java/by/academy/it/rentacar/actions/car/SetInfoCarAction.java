/**
 * 
 */
package by.academy.it.rentacar.actions.car;


/**
 * Class SetInfoCarAction
 * 
 * Class SetInfoCarAction returns the list to select and specify the characteristics of the new car
 * 
 * @author Fadeeva Natallia
 * @version 1.0
 * @since 2016-03
 *
 */
//public class SetInfoCarAction extends Action {
//
//	@Override
//	public String execute(HttpServletRequest request, HttpServletResponse response) {
//		getListFilterCar(request, 1);
//		// set value of filter
//		// Transmission
//		try {
//			String transmission = request.getParameter("transmission").trim();
//			if (transmission.equals("0")) {
//				request.setAttribute("transmission", Transmission.MANUAL);
//			} else {
//				request.setAttribute("transmission", Transmission.valueOf(transmission));
//			}
//		} catch (Exception e) {
//			request.setAttribute("transmission", Transmission.MANUAL);
//		}
//		// Fuels
//		try {
//			String fuelId = request.getParameter("fuelId").trim();
//			if (!fuelId.equals("0")) {
//				request.setAttribute("fuelId", Integer.parseInt(fuelId));
//			}
//		} catch (Exception e) {
//			request.setAttribute("fuelId", 1);
//		}
//		// Type
//		try {
//			String typeId = request.getParameter("typeId").trim();
//			if (!typeId.equals("0")) {
//				request.setAttribute("typeId", Integer.parseInt(typeId));
//			}
//		} catch (Exception e) {
//			request.setAttribute("typeId", 1);
//		}
//		// Rating
//		try {
//			String ratingId = request.getParameter("ratingId");
//			if (!ratingId.equals("0")) {
//				request.setAttribute("ratingId", Integer.parseInt(ratingId));
//			}
//		} catch (Exception e) {
//			request.setAttribute("ratingId", 1);
//		}
//		// Model and mark
//		try {
//			String modelId = request.getParameter("modelId");
//			if (!modelId.equals("0")) {
//				request.setAttribute("modelId", Integer.parseInt(modelId));
//			}
//		} catch (Exception e) {
//			request.setAttribute("modelId", 1);
//		}
//		request.setAttribute("addCar", 0);
//		return ConfigurationManager.getProperty("page.addCar");
//	}
//
//}
