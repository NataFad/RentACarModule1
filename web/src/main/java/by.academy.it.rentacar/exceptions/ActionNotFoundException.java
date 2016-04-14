/**
 * 
 */
package by.academy.it.rentacar.exceptions;

import org.apache.log4j.Logger;

/**
 * Class ActionNotFoundException extends Exception displays an error message
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
@SuppressWarnings("serial")
public class ActionNotFoundException extends Exception {
	
	public ActionNotFoundException(String string) {
		Logger.getLogger(this.getClass()).error(string);
	}
}
