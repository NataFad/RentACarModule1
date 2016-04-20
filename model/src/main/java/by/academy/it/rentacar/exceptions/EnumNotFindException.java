/**
 * 
 */
package by.academy.it.rentacar.exceptions;

import org.apache.log4j.Logger;

/**
 * Class EnumNotFindException extends Exception displays an error message
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class EnumNotFindException extends Exception {

	public EnumNotFindException(String message) {
		Logger.getLogger(this.getClass()).error(message);
	}

}
