package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.dao.*;
import by.academy.it.rentacar.entity.*;
import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.exceptions.DAOException;
import by.academy.it.rentacar.util.HibernateUtil;
import by.academy.it.rentacar.viewobject.CarViewObject;
import org.apache.log4j.Logger;
import org.hibernate.Transaction;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Class CarService
 * <p>
 * Class CarService induces CarDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
public class CarService implements ICarService{

    private volatile static CarService instance;
    private static Logger log = Logger.getLogger(CarService.class);

    private CarService() {
    }

    public static CarService getInstance() {
        if (instance == null) {
            synchronized (CarService.class) {
                if (instance == null) {
                    instance = new CarService();
                }
            }
        }
        return instance;
    }

    /**
     * Method registeredCar() checks the validity of the derived parameters,
     *  creates an object Car calls method add() in CarDAO
     *
     * @param parametresCar
     * @return successReg
     */
    public Car registeredCar(HashMap<String, String> parametresCar) {
        Car car = null;
        Transaction transaction = HibernateUtil.getInstance().getSession().getTransaction();
        if (!transaction.isActive()){
            transaction.begin();
        }

        Transmission transmission = null;
        String transmissionString = parametresCar.get("transmission");
        if (transmissionString != null) {
            transmission = Transmission.valueOf(transmissionString);
        } else {
            transaction.rollback();
            log.error("Transmission is not set");
            return car;
        }

        // get ratecost by rating
        int ratingId = Integer.parseInt(parametresCar.get("ratingId"));
        BigDecimal ratecostByRating = new BigDecimal(0);
        Rating rating = RatingDAO.getInstance().getById(ratingId);
        if (rating == null) {
            transaction.rollback();
            log.error("Rating is not set");
            return car;
        }
        ratecostByRating = rating.getRateCost();

        // get ratecost and rateDiscount by type
        int typeId = Integer.parseInt(parametresCar.get("typeId"));
        BigDecimal ratecostByType = new BigDecimal(0);
        BigDecimal rateDiscountByType = new BigDecimal(0);
        Type type = TypeDAO.getInstance().getById(typeId);
        if (type == null) {
            transaction.rollback();
            log.error("Type is not set");
            return car;
        }
        ratecostByType = type.getRateCost();
        rateDiscountByType = type.getRateDiscount();

        // fuel
        int fuelId = Integer.parseInt(parametresCar.get("fuelId"));
        Fuel fuel = FuelDAO.getInstance().getById(fuelId);
        if (fuel == null) {
            transaction.rollback();
            log.error("Fuel is not set");
            return car;
        }

        // price
        Price priceCar = null;
        try {
            priceCar = PriceService.getInstance().getByTransmissionAndFuel(transmission, fuel);
        } catch (DAOException e) {
            transaction.rollback();
            log.error("Price is not found by transmission " + transmission.getName()
                    + " and fuel " + fuel.toString());
        }
        if (priceCar == null) {
            transaction.rollback();
            log.error("Price is not found by transmission " + transmission.getName()
                    + " and fuel " + fuel.toString());
            return car;
        }

        // calculate the cost per day
        BigDecimal costOfDay = priceCar.getCostOfDay().multiply(ratecostByRating).multiply(ratecostByType);
        if (costOfDay.equals(new BigDecimal(0))) {
            transaction.rollback();
            log.error("The price is not calculated");
            return car;
        }

        // registration number
        String regNumber = parametresCar.get("registrationNumber");
        if (regNumber == null) {
            transaction.rollback();
            log.error("The registration number of the car is not set");
            return car;
        }

        // model and mark
        int modelId = Integer.parseInt(parametresCar.get("modelId"));
        ModelAndMark model = ModelAndMarkDAO.getInstance().getById(modelId);
        if (model == null) {
            transaction.rollback();
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
        try {
            CarDAO.getInstance().saveOrUpdate(car);
            if (!transaction.wasCommitted()) {
                transaction.commit();
            }
        } catch (DAOException e) {
            log.error(e.getMessage());
            transaction.rollback();
        }
        return car;
    }

    /**
     * Metod getAllCars() calls method getAll() in CarDAO
     *
     * @return
     */
    public ArrayList<Car> getAllCars() {
        ArrayList<Car> carList = null;
        Transaction transaction = HibernateUtil.getInstance().getSession().getTransaction();
        try {
            carList = (ArrayList<Car>) CarDAO.getInstance().getAll();
            if (!transaction.wasCommitted()) {
                transaction.commit();
            }
        } catch (DAOException e) {
            log.error(e.getMessage());
            transaction.rollback();
        }
        return carList;
    }

    public List<CarViewObject> getSearchCar(Date fromDate, Date byDate, HashMap<String, String> filterValues) {
        Transaction transaction = HibernateUtil.getInstance().getSession().getTransaction();
        if (!transaction.isActive()){
            transaction.begin();
        }

        List<CarViewObject> carList = null;
        if (fromDate.compareTo(byDate) != -1) {
            try {
                carList = CarDAO.getInstance().searchCar(fromDate, byDate, filterValues);
                if (!transaction.wasCommitted()) {
                    transaction.commit();
                }
            } catch (DAOException e) {
                log.error(e.getMessage());
                transaction.rollback();
            }
        } else {
            if (!transaction.wasCommitted()) {
                transaction.commit();
            }
        }
        return carList;
    }
}
