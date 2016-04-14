package by.academy.it.rentacar.managers;

import java.util.ResourceBundle;

/**
 * Class ErrorManager
 *
 * Class ErrorManager provides comfortable way to log errors.
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class ErrorManager {

	private volatile static ErrorManager instance;
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");
	  
	private ErrorManager() {
	}

	public static ErrorManager getInstance() {
		if (instance == null) {
			synchronized (ErrorManager.class) {
				if (instance == null) {
					instance = new ErrorManager();
				}
			}
		}
		return instance;
	}

	public String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
