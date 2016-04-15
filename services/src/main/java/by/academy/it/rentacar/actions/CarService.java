package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.beans.Car;
import by.academy.it.rentacar.dao.CarDAO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class CarService
 *
 * Class CarService induces CarDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class CarService {

    private volatile static CarService instance;

    private CarService(){}

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
     * Method register() calls method add() in CarDAO
     *
     * @param car
     * @return successReg
     */
    public int register(Car car){
      int successReg = 1;
      CarDAO.getInstance().add(car);
      return successReg;
    }

    /**
     * Metod getAllCars() calls method getAll() in CarDAO
     *
     * @return
     */
    public ArrayList<Car> getAllCars(){
        ArrayList<Car> carList = new ArrayList<Car>();
        carList = CarDAO.getInstance().getAll();
        return carList;
    }

    public ArrayList<Car> getSearchCar(Date fromDate, Date byDate,  HashMap<String, String> filterValues){
        ArrayList<Car> carList = new ArrayList<Car>();
        carList = CarDAO.getInstance().searchCar(fromDate, byDate, filterValues);
        return carList;
    }
}
