package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.dao.PriceDAO;
import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.exceptions.DAOException;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.Price;

/**
 * Class PriceService induces PriceDAO
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 *
 */
public class PriceService implements IPriceService{

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
    public Price getByTransmissionAndFuel(Transmission transmission, Fuel fuel) {
        Price price = null;
        try {
            price = PriceDAO.getInstance().getByTransmissionAndFuel(transmission, fuel);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return price;
    }
}
