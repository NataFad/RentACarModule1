/**
 *
 */
package by.academy.it.rentacar.controller.car;

import by.academy.it.rentacar.actions.IActionService;
import by.academy.it.rentacar.actions.ICarService;
import by.academy.it.rentacar.actions.IRatingService;
import by.academy.it.rentacar.actions.ITypeService;
import by.academy.it.rentacar.entity.Fuel;
import by.academy.it.rentacar.entity.ModelAndMark;
import by.academy.it.rentacar.entity.Rating;
import by.academy.it.rentacar.entity.Type;
import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.managers.ErrorManager;
import by.academy.it.rentacar.viewobject.CarVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Class GetAllCarsAction
 * <p>
 * Class GetAllCarsAction returns a list of all cars.
 *
 * @author Fadeeva Natallia
 * @version 1.0
 * @since 2016-03
 */
@Controller
public class CarController {

    static Logger log = Logger.getLogger(CarController.class);
    @Autowired
    private ICarService carService;
    @Autowired
    private IActionService actionService;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private IRatingService ratingService;

    private ErrorManager errorManager = ErrorManager.getInstance();

    /**
     * Method formateDate() transforms date into "yyyy-MM-dd"
     *
     * @param toDate
     * @return string
     */
    public String formateDate(java.util.Date toDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        return dateFormat.format(toDate);
    }

    /**
     * Method getListFilterCar() generates lists of filters for search of car
     *
     * @param modelMap
     * @param getModels
     */
    public void getListFilterCar(ModelMap modelMap, int getModels) {
        // List of transmission
        ArrayList<Transmission> transList = actionService.getListTransmission();
        modelMap.addAttribute("transList", transList);
        // List of fuels
        ArrayList<Fuel> fuelsList = actionService.getListFuel();
        if (fuelsList.isEmpty()) {
            modelMap.addAttribute("errorFilterCarMassager", errorManager.getProperty("search.error"));
        }
        modelMap.addAttribute("fuelsList", fuelsList);
        // List of types
        ArrayList<Type> typeList = typeService.getListType();
        if (typeList.isEmpty()) {
            modelMap.addAttribute("errorFilterCarMassager", errorManager.getProperty("search.error"));
        }
        modelMap.addAttribute("typeList", typeList);
        // List of ratings
        ArrayList<Rating> ratingList = ratingService.getListRating();
        if (ratingList.isEmpty()) {
            modelMap.addAttribute("errorFilterCarMassager", errorManager.getProperty("search.error"));
        }
        modelMap.addAttribute("ratingList", ratingList);
        // List of models
        if (getModels == 1) {
            ArrayList<ModelAndMark> modelList = actionService.getListModel();
            if (modelList.isEmpty()) {
                modelMap.addAttribute("errorFilterCarMassager", errorManager.getProperty("search.error"));
            }
            modelMap.addAttribute("modelList", modelList);
        }
        // List of transmission
        ArrayList<String> perPageList = actionService.getListPerPage();
        modelMap.addAttribute("perPageList", perPageList);
        modelMap.addAttribute("recordPerPage", "3");
    }

    @RequestMapping(value = "/allCars")
    public String showCars(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "1") Integer page, HttpSession httpSession) {
        // period
        java.sql.Date fromDate = new java.sql.Date(new java.util.Date().getTime());
        modelMap.addAttribute("fromDate", formateDate(fromDate));
        modelMap.addAttribute("byDate", formateDate(fromDate));

        getListFilterCar(modelMap, 0);

        int recordsPerPage = 10;
        HashMap<String, String> filterValues = new HashMap<String, String>();
        filterValues.put("page", Integer.toString(page));
        filterValues.put("recordsPerPage", Integer.toString(recordsPerPage));

        List<CarVO> list = carService.getSearchCar(fromDate, fromDate, filterValues);
        if (list.isEmpty()) {
            list = null;
            modelMap.addAttribute("search_result", list);
            modelMap.addAttribute("errorSearchCarMessage", errorManager.getProperty("search.nullsearch"));
        }
        modelMap.addAttribute("search_result", list);

        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("maxPages", carService.calculateMaxPages(fromDate, fromDate, filterValues, recordsPerPage));

        return "searchcar";
    }

    @RequestMapping(value = "/search_cars")
    public String searchCars(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @ModelAttribute HashMap<String, String> filterList, HttpSession httpSession) {

        Iterator it = filterList.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            log.debug(pair.getKey() + " = " + pair.getValue());
        }

        modelMap.clear();
        // period
        Date fromDate = Date.valueOf(filterList.get("fromDate").trim());
        Date byDate = Date.valueOf(filterList.get("byDate").trim());
        HashMap<String, String> filterValues = new HashMap<String, String>();
        // Transmission
        String transmission = filterList.get("transmission").trim();
        if (!transmission.equals("")) {
            filterValues.put("transmission", transmission);
            modelMap.addAttribute("transmission", transmission);
        }
        // Fuels
        String fuelId = filterList.get("fuelId").trim();
        if (!fuelId.equals("0")) {
            filterValues.put("fuelId", fuelId);
            modelMap.addAttribute("fuelId", Integer.parseInt(fuelId));
        }

        // Type
        String typeId = filterList.get("typeId").trim();
        if (!typeId.equals("0")) {
            filterValues.put("typeId", typeId);
            modelMap.addAttribute("typeId", Integer.parseInt(typeId));
        }

        // Rating
        String ratingId = filterList.get("ratingId");
        if (!ratingId.equals("0")) {
            filterValues.put("ratingId", ratingId);
            modelMap.addAttribute("ratingId", Integer.parseInt(ratingId));
        }

        // recordsPerPage
        String recordPerPage = filterList.get("recordPerPage");
        filterValues.put("recordsPerPage", recordPerPage);
        int carPerPage = Integer.parseInt(recordPerPage);
        modelMap.addAttribute("recordPerPage", carPerPage);
        filterValues.put("page", "1");
        modelMap.addAttribute("page", 1);

        // period
        modelMap.addAttribute("fromDate", formateDate(fromDate));
        modelMap.addAttribute("byDate", formateDate(byDate));
        getListFilterCar(modelMap, 0);

        int count = carService.countCarByFilter(fromDate, byDate, filterValues);
        modelMap.addAttribute("errorFilterCarMassager", "Count by filter " + count);

        HashMap<String, Object> filterResponse = new HashMap<String, Object>();
        List<CarVO> list = carService.getSearchCar(fromDate, byDate, filterValues);
        if (list.isEmpty()) {
            list = null;
            modelMap.addAttribute("search_result", list);
            modelMap.addAttribute("errorSearchCarMessage", errorManager.getProperty("search.nullsearch"));
        }
        modelMap.addAttribute("search_result", list);

        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("maxPages", carService.calculateMaxPages(fromDate, byDate, filterValues, carPerPage));

        return "searchcar";
    }
}
