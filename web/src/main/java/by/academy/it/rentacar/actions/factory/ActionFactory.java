package by.academy.it.rentacar.actions.factory;

import by.academy.it.rentacar.actions.Action;
import by.academy.it.rentacar.actions.user.RegisterUserAction;
import by.academy.it.rentacar.actions.InDeveloping;
import by.academy.it.rentacar.actions.car.AddCarAction;
import by.academy.it.rentacar.actions.car.GetAllCarsAction;
import by.academy.it.rentacar.actions.car.SearchCarAction;
import by.academy.it.rentacar.actions.car.SetInfoCarAction;
import by.academy.it.rentacar.actions.user.ExitUserAction;
import by.academy.it.rentacar.actions.user.LoginUserAction;
import by.academy.it.rentacar.exceptions.ActionNotFoundException;
import by.academy.it.rentacar.util.HibernateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * Class ActionFactory
 *
 * Class ActionFactory contains possible actions of the application
 *
 * @author Fadeeva Natallia
 * @version 1.0
 * @since 2016-03
 * 
 */
public class ActionFactory {
	private volatile static ActionFactory instance;
	private HashMap<String, Action> container;

	private ActionFactory() {
		if (container == null) {
			container = new HashMap<String, Action>();
			container.put("login", new LoginUserAction());
			container.put("exit", new ExitUserAction());
			container.put("registration", new RegisterUserAction());
			container.put("get_all_cars", new GetAllCarsAction());
			container.put("search_cars", new SearchCarAction());
			container.put("setInfoCar", new SetInfoCarAction());
			container.put("addCarAction", new AddCarAction());
			container.put("inDeveloping", new InDeveloping());
		}
	}

	public static ActionFactory getInstance() {
		if (instance == null) {
			synchronized (ActionFactory.class) {
				if (instance == null) {
					instance = new ActionFactory();
				}
			}
		}
		return instance;
	}

	public Action getAction(HttpServletRequest request) throws ActionNotFoundException {
		String actionName = request.getParameter("command");// 'command' means action name
		HibernateUtil.getInstance().getSession();
		if (actionName != null) {
			Action action = container.get(actionName);
			if (action == null) {
				throw new ActionNotFoundException(actionName);
			} else {
				return action;
			}
		}		
		throw new IllegalArgumentException("Empty an parameter");
	}
}
