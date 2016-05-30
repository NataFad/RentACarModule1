package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.ModelAndMark;

import java.util.ArrayList;

/**
 * Created by Nata on 23.05.2016.
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 */
public interface IActionService {

    ArrayList<Transmission> getListTransmission();

    ArrayList<Fuel> getListFuel();

    ArrayList<ModelAndMark> getListModel();
}
