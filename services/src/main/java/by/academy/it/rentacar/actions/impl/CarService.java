package by.academy.it.rentacar.actions.impl;


import by.academy.it.rentacar.actions.ICarService;
import by.academy.it.rentacar.actions.IPriceService;
import by.academy.it.rentacar.dao.*;
import by.academy.it.rentacar.entity.*;
import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.viewobject.CarVO;
import by.academy.it.rentacar.viewobject.FilterVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class CarService
 * <p>
 * Class CarService induces CarDAO
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@Service("carService")
@Transactional(readOnly = true)
public class CarService implements ICarService {

    @Autowired
    private ICarDAO carDAO;
    @Autowired
    private IRatingDAO ratingDAO;
    @Autowired
    private IFuelDAO fuelDAO;
    @Autowired
    private ITypeDAO typeDAO;
    @Autowired
    private IModelAndMarkDAO modelAndMarkDAO;
    @Autowired
    private IPriceService priceService;

    private static Logger log = Logger.getLogger(CarService.class);

    /**
     * Method registeredCar() checks the validity of the derived parameters,
     * creates an object Car calls method add() in CarDAO
     *
     * @param parametresCar
     * @return successReg
     */
    @Transactional(readOnly = false)
    public Car registeredCar(HashMap<String, String> parametresCar) {
        Car car = null;
        Transmission transmission = null;
        String transmissionString = parametresCar.get("transmission");
        if (transmissionString != null) {
            transmission = Transmission.valueOf(transmissionString);
        } else {
            log.error("Transmission is not set");
            return car;
        }
        // get ratecost by rating
        int ratingId = Integer.parseInt(parametresCar.get("ratingId"));
        BigDecimal ratecostByRating = new BigDecimal(0);
        Rating rating = ratingDAO.getById(ratingId);
        if (rating == null) {
            log.error("Rating is not set");
            return car;
        }
        ratecostByRating = rating.getRateCost();
        // get ratecost and rateDiscount by type
        int typeId = Integer.parseInt(parametresCar.get("typeId"));
        BigDecimal ratecostByType = new BigDecimal(0);
        BigDecimal rateDiscountByType = new BigDecimal(0);
        Type type = typeDAO.getById(typeId);
        if (type == null) {
            log.error("Type is not set");
            return car;
        }
        ratecostByType = type.getRateCost();
        rateDiscountByType = type.getRateDiscount();
        // fuel
        int fuelId = Integer.parseInt(parametresCar.get("fuelId"));
        Fuel fuel = fuelDAO.getById(fuelId);
        if (fuel == null) {
            log.error("Fuel is not set");
            return car;
        }
        // price
        Price priceCar = null;
        priceCar = priceService.getByTransmissionAndFuel(transmission, fuel);
        if (priceCar == null) {
            log.error("Price is not found by transmission " + transmission.getName()
                    + " and fuel " + fuel.toString());
            return car;
        }
        // calculate the cost per day
        BigDecimal costOfDay = priceCar.getCostOfDay().multiply(ratecostByRating).multiply(ratecostByType);
        if (costOfDay.equals(new BigDecimal(0))) {
            log.error("The price is not calculated");
            return car;
        }
        // registration number
        String regNumber = parametresCar.get("registrationNumber");
        if (regNumber == null) {
            log.error("The registration number of the car is not set");
            return car;
        }
        // model and mark
        int modelId = Integer.parseInt(parametresCar.get("modelId"));
        ModelAndMark model = modelAndMarkDAO.getById(modelId);
        if (model == null) {
            log.error("Model and mark is not set");
            return car;
        }
        // car
        car = new Car();
        car.setRegistrationNumber(regNumber);
        car.setModel(model);
        car.setTransmission(transmission);
        car.setFuel(fuel);
        car.setType(type);
        car.setRating(rating);
        car.setDescription(parametresCar.get("description"));
        car.setPrice(priceCar);
        car.setCostOfDay(costOfDay);
        car.setDiscount(priceCar.getDiscount().multiply(rateDiscountByType).multiply(new BigDecimal(100)));
        log.error(car);
        carDAO.saveOrUpdate(car);
        return car;
    }

    /**
     * Metod getAllCars() calls method getAll() in CarDAO
     *
     * @return carList
     */
    public ArrayList<Car> getAllCars() {
        ArrayList<Car> carList = (ArrayList<Car>) carDAO.getAll();
        return carList;
    }

    public List<CarVO> getSearchCar(FilterVO filterVO, int page) {
        List<CarVO> carList = null;
        if (filterVO.getFromDate().compareTo(filterVO.getByDate()) != -1) {
            carList = carDAO.searchCar(filterVO, page);
        } else {
            log.error("Incorrectly stated the date");
        }
        return carList;
    }

    public int countCarByFilter(FilterVO filterVO){
        int countCar = carDAO.countCarByFilter(filterVO);
        return countCar;
    }

    public int calculateMaxPages(FilterVO filterVO){
        int count = countCarByFilter(filterVO);
        int carPerPage = filterVO.getRecordPerPage();
        int maxPage = (count % carPerPage == 0) ?
                (count / carPerPage) :
                (count / carPerPage + 1);

        return maxPage;
    }

}
