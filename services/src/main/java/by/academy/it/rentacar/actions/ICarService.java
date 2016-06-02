package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.viewobject.CarVO;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nata on 22.05.2016.
 *
 */
public interface ICarService {
    Car registeredCar(HashMap<String, String> parametresCar);
    List<CarVO> getSearchCar(Date fromDate, Date byDate, HashMap<String, String> filterValues);
    ArrayList<Car> getAllCars();
    int countCarByFilter(Date fromDate, Date byDate, HashMap<String, String> filterValues);
    int calculateMaxPages(Date fromDate, Date byDate, HashMap<String, String> filterValues, int carPerPage);
}
