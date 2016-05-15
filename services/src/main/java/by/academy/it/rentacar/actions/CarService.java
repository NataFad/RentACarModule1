package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.dao.CarDAO;
import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.exceptions.DAOException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
        try {
            CarDAO.getInstance().saveOrUpdate(car);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return successReg;
    }

    /**
     * Metod getAllCars() calls method getAll() in CarDAO
     *
     * @return
     */
    public ArrayList<Car> getAllCars(){
        ArrayList<Car> carList = null;
        try {
            carList = (ArrayList<Car>) CarDAO.getInstance().getAll();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public List<Car> getSearchCar(Date fromDate, Date byDate,  HashMap<String, String> filterValues){
        List<Car> carList = CarDAO.getInstance().searchCar(fromDate, byDate, filterValues);
        return carList;
    }
}
