package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.entity.Car;
import by.academy.it.rentacar.viewobject.CarVO;
import by.academy.it.rentacar.viewobject.FilterVO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Nata on 22.05.2016.
 *
 */
public interface ICarService {
    Car registeredCar(HashMap<String, String> parametresCar);
    List<CarVO> getSearchCar(FilterVO filterVO, int page);
    ArrayList<Car> getAllCars();
    int countCarByFilter(FilterVO filterVO);
    int calculateMaxPages(FilterVO filterVO);
}
