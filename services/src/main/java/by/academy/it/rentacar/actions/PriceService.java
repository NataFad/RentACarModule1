package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.dao.PriceDAO;
import by.academy.it.rentacar.enums.Transmission;

/**
 * Class PriceService induces PriceDAO
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class PriceService {

    private volatile static PriceService instance;

    private PriceService(){}

    public static PriceService getInstance() {
        if (instance == null) {
            synchronized (PriceService.class) {
                if (instance == null) {
                    instance = new PriceService();
                }
            }
        }
        return instance;
    }

    /**
     * Method getByTransmissionAndFuel() calls the method in PriceDAO
     *
     * @param transmission
     * @param fuel
     * @return
     */
    public Price getByTransmissionAndFuel(Transmission transmission, Fuel fuel){
        Price price = null;
        price = PriceDAO.getInstance().getByTransmissionAndFuel(transmission, fuel);
        return price;
    }
}
