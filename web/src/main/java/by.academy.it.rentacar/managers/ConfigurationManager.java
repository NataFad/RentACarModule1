package by.academy.it.rentacar.managers;

import java.util.ResourceBundle;

/**
 * Class ConfigurationManager
 * 
 * Class ConfigurationManager retrieves the information from the file
 * config.properties
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class ConfigurationManager {
	private volatile static ConfigurationManager instance;
	private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

	private ConfigurationManager() {
	}
	 
	public static ConfigurationManager getInstance() {
		if (instance == null) {
			synchronized (ConfigurationManager.class) {
				if (instance == null) {
					instance = new ConfigurationManager();
				}
			}
		}
		return instance;
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}