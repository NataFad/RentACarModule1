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
import by.academy.it.rentacar.enums.ElementsPerPage;
import by.academy.it.rentacar.enums.Transmission;
import by.academy.it.rentacar.managers.ErrorManager;
import by.academy.it.rentacar.viewobject.CarVO;
import by.academy.it.rentacar.viewobject.FilterVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

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
        ArrayList<ElementsPerPage> perPageList = actionService.getListPerPage();
        modelMap.addAttribute("perPageList", perPageList);
    }

    @InitBinder
    public final void initBinderUsuariosFormValidator(final WebDataBinder binder, final Locale locale) {
        final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", locale);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    public java.sql.Date trasformToSQLDate(Date utilDate) {
        return new java.sql.Date(utilDate.getTime());
    }

    @RequestMapping(value = "/allCars")
    public String showCars(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "1") Integer page, HttpSession httpSession){
        // period
        Date dateNow = new Date();
        FilterVO filterVO = new FilterVO();
        filterVO.setFromDate(dateNow);
        filterVO.setByDate(dateNow);
        filterVO.setTransmission("");
        filterVO.setRecordPerPage(ElementsPerPage.FIVE.getValue());

        getListFilterCar(modelMap, 0);

        List<CarVO> list = carService.getSearchCar(filterVO, page);
        if (list.isEmpty()) {
            list = null;
            modelMap.addAttribute("search_result", list);
            modelMap.addAttribute("errorSearchCarMessage", errorManager.getProperty("search.nullsearch"));
        }
        modelMap.addAttribute("search_result", list);
        modelMap.addAttribute("filter", filterVO);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("maxPages", carService.calculateMaxPages(filterVO));
        modelMap.addAttribute("dateFrom", trasformToSQLDate(dateNow).toString());
        modelMap.addAttribute("dateBy", trasformToSQLDate(dateNow).toString());
        httpSession.setAttribute("filter", filterVO);
        log.debug("Filter: " + filterVO);
        log.debug("ModelMap: " + modelMap);
        return "searchcar";
    }

    public FilterVO checkFilter(FilterVO filter, Date dateFrom, Date dateBy){
        if (dateFrom != null) {
            filter.setFromDate(dateFrom);
        }
        if (dateBy != null) {
            filter.setByDate(dateBy);
        }
        String transmission = null;
        try {
            transmission = filter.getTransmission();
        } catch (Exception e) {
            filter.setTransmission("");
        }
        if (transmission == null) {
            filter.setTransmission("");
        }
        return filter;
    }

    public ModelMap searchCars(ModelMap modelMap, FilterVO filter, Date dateFrom, Date dateBy, int page) {
        checkFilter(filter, dateFrom, dateBy);
        modelMap.clear();
        getListFilterCar(modelMap, 0);

        int count = carService.countCarByFilter(filter);
        modelMap.addAttribute("errorFilterCarMassager", "Count by filter " + count);

        List<CarVO> list = carService.getSearchCar(filter, page);
        if (list.isEmpty()) {
            list = null;
            modelMap.addAttribute("search_result", list);
            modelMap.addAttribute("errorSearchCarMessage", errorManager.getProperty("search.nullsearch"));
        }
        filter.setRecordPerPage(ElementsPerPage.FIVE.getValue());
        modelMap.addAttribute("search_result", list);
        modelMap.addAttribute("filter", filter);
        modelMap.addAttribute("currentPage", page);
        modelMap.addAttribute("maxPages", carService.calculateMaxPages(filter));
        modelMap.addAttribute("dateFrom", trasformToSQLDate(dateFrom).toString());
        modelMap.addAttribute("dateBy", trasformToSQLDate(dateBy).toString());
        return modelMap;
    }

    @RequestMapping(value = "/search_cars", method = RequestMethod.POST)
    public String searchCarsPost(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "1") Integer page,
                             @ModelAttribute(value = "filter") FilterVO filter, Date dateFrom, Date dateBy, HttpSession httpSession) {
        log.debug("Filter: " + filter);
        log.debug("ModelMap: " + modelMap);
        modelMap = searchCars(modelMap, filter, dateFrom, dateBy, page);
        httpSession.setAttribute("filter", filter);
        return "searchcar";
    }

    @RequestMapping(value = "/search_cars", method = RequestMethod.GET)
    public String searchCarsGet(ModelMap modelMap, @RequestParam(value = "page", defaultValue = "1") Integer page,
                             HttpSession httpSession) {
        FilterVO filter;
        filter = (FilterVO) httpSession.getAttribute("filter");
        log.debug("Filter: " + filter);
        log.debug("ModelMap: " + modelMap);
        Date dateFrom = filter.getFromDate();
        Date dateBy = filter.getByDate();
        modelMap = searchCars(modelMap, filter, dateFrom, dateBy, page);
        httpSession.setAttribute("filter", filter);
        return "searchcar";
    }
}
