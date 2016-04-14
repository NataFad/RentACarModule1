/**
 * 
 */
package by.academy.it.rentacar.actions;


import by.academy.it.rentacar.managers.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class InDeveloping
 * 
 * Class InDeveloping reported that the development is in functional
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class InDeveloping extends Action {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) {
		return ConfigurationManager.getProperty("page.inDeveloping");
	}

	
}
