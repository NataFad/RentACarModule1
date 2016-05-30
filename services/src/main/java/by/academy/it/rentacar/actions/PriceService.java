package by.academy.it.rentacar.actions;

import by.academy.it.rentacar.dao.IPriceDAO;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.Price;
import by.academy.it.rentacar.enums.Transmission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Class PriceService induces PriceDAO
 *
 * @author Fadeeva Natallia
 * @version 1.3
 * @since 2016-05
 */
@Service("priceService")
@Transactional(readOnly = true)
public class PriceService implements IPriceService {

    @Autowired
    private IPriceDAO priceDAO;

    /**
     * Method getByTransmissionAndFuel() calls the method in PriceDAO
     *
     * @param transmission
     * @param fuel
     * @return
     */
    public Price getByTransmissionAndFuel(Transmission transmission, Fuel fuel) {
        Price price = priceDAO.getByTransmissionAndFuel(transmission, fuel);
        return price;
    }
}
