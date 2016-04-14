package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.beans.Price;
import by.academy.it.rentacar.dao.PriceDAO;
import by.academy.it.rentacar.enums.Transmission;
import org.apache.log4j.Logger;

import java.sql.SQLException;

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
     * @param fuelId
     * @return
     */
    public Price getByTransmissionAndFuel(Transmission transmission, int fuelId){
        Price price = null;
        try {
            price = PriceDAO.getInstance().getByTransmissionAndFuel(transmission, fuelId);
        } catch (SQLException e) {
            Logger.getLogger(PriceDAO.class).error(e.getMessage());
        }
        return price;
    }
}
