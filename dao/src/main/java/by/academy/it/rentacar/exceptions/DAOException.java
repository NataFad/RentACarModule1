/**
 * 
 */
package by.academy.it.rentacar.exceptions;

import org.apache.log4j.Logger;

/**
 * Class DAOException extends Exception displays an error message
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class DAOException extends Exception {

	public DAOException(String message) {
		Logger.getLogger(this.getClass()).error(message);
	}

}
