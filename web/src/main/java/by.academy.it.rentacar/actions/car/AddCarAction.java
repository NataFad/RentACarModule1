/**
 * 
 */
package by.academy.it.rentacar.actions.car;

import by.academy.it.rentacar.actions.*;
import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.entity.Rating;
import by.academy.it.rentacar.entity.Type;
import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.managers.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;

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

	private Price priceCar;

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute("addCar", 1);
		
		Transmission transmission = Transmission.valueOf(request.getParameter("transmission").trim());
		int fuelId = Integer.parseInt(request.getParameter("fuelId").trim());
		int typeId = Integer.parseInt(request.getParameter("typeId").trim());
		int ratingId = Integer.parseInt(request.getParameter("ratingId").trim());

		// get ratecost by rating
		BigDecimal ratecostByRating = new BigDecimal(0);
		Rating rating = RatingService.getInstance().getById(ratingId);
		if (rating == null) {
			request.getSession().setAttribute("addCarMessage", errorManager.getProperty("error.addcar"));
			return ConfigurationManager.getProperty("page.addCar");
		}
		ratecostByRating = rating.getRateCost();

		// get ratecost and rateDiscount by type
		BigDecimal ratecostByType = new BigDecimal(0);
		BigDecimal rateDiscountByType = new BigDecimal(0);
		Type type = TypeService.getInstance().getById(typeId);
		if (type == null) {
			request.getSession().setAttribute("addCarMessage", errorManager.getProperty("error.addcar"));
			return ConfigurationManager.getProperty("page.addCar");
		}
		ratecostByType = type.getRateCost();
		rateDiscountByType = type.getRateDiscount();

		priceCar = PriceService.getInstance().getByTransmissionAndFuel(transmission, fuelId);
		if (priceCar == null) {
			request.getSession().setAttribute("addCarMessage", errorManager.getProperty("error.priceCar") + errorManager.getProperty("error.addcar"));
			return ConfigurationManager.getProperty("page.addCar");
		}

		// calculate the cost per day
		BigDecimal costOfDay = priceCar.getCostOfDay().multiply(ratecostByRating).multiply(ratecostByType);
		if (costOfDay.equals(new BigDecimal(0))) {
			request.getSession().setAttribute("addCarMessage",
					errorManager.getProperty("error.priceCar") + " "+ errorManager.getProperty("error.addcar"));
			return ConfigurationManager.getProperty("page.addCar");
		}
		
		// car
		Car car = new Car();
		car.setRegistrationNumber(request.getParameter("registrationNumber").trim());
		car.setModelAndMarkId(Integer.parseInt(request.getParameter("modelId").trim()));
		car.setTransmission(transmission);
		car.setFuelId(fuelId);
		car.setTypeId(typeId);
		car.setRatingId(ratingId);
		car.setDescription(request.getParameter("description").trim());
		car.setPriceId(priceCar.getId());
		car.setCostOfDay(costOfDay);
		car.setDiscount(priceCar.getDiscount().multiply(rateDiscountByType).multiply(new BigDecimal(100)));

		int successReg = CarService.getInstance().register(car);
		if (successReg == 0){
			request.getSession().setAttribute("addCarMessage",  errorManager.getProperty("error.addcar"));
			return ConfigurationManager.getProperty("page.addCar");
		}
		request.getSession().setAttribute("addCarMessage", 
				errorManager.getProperty("success.addcar") + " "+ car.toString());
		return ConfigurationManager.getProperty("page.addCar");
	}

}
