
package by.academy.it.rentacar.managers;

import java.util.ResourceBundle;

/**Class DBSqlManager
 * 
 * Class DBSqlManager provides access to prepared statements, which contains in sql.properties file.
 * 
 * @author Fadeeva Natallia
 * @version 1.0
 * @since 2016-03
 * 
 */
public class DBSqlManager {
	private volatile static DBSqlManager instance;
	private static ResourceBundle resourceBundle;

	public static final String FILE_NAME = "sql";

	// FUEL DAO
	public static final String SQL_GET_ALL_FUELS = "SQL_GET_ALL_FUELS";
	public static final String SQL_ADD_FUEL = "SQL_ADD_FUEL";
	public static final String SQL_UPDATE_FUEL = "SQL_UPDATE_FUEL";
	public static final String SQL_GET_FUELS_BY_ID = "SQL_GET_FUELS_BY_ID";
	public static final String SQL_SEARCH_FUEL = "SQL_SEARCH_FUEL";
	public static final String SQL_COUNT_FUELS = "SQL_COUNT_FUELS";

	// USER DAO
	public static final String SQL_ADD_USER = "SQL_ADD_USER";
	public static final String SQL_UPDATE_USER = "SQL_UPDATE_USER";
	public static final String SQL_UPDATE_ACCESS = "SQL_UPDATE_ACCESS";
	public static final String SQL_SET_ACCESS = "SQL_GET_ACCESS";
	public static final String SQL_CHECK_LOGIN = "SQL_CHECK_LOGIN";
	public static final String SQL_GET_USER = "SQL_GET_USER";
	public static final String SQL_GET_USER_BY_ID = "SQL_GET_USER_BY_ID";
	public static final String SQL_COUNT_USERS = "SQL_COUNT_USERS";
	public static final String SQL_GET_ALL_USERS = "SQL_GET_ALL_USERS";

	// CAR DAO
	public static final String SQL_GET_ALL_CARS = "SQL_GET_ALL_CARS";
	public static final String SQL_ADD_CAR = "SQL_ADD_CAR";
	public static final String SQL_DELETE_CAR = "SQL_DELETE_CAR";
	
	// RATING DAO
	public static final String SQL_GET_RATINGS_BY_ID = "SQL_GET_RATINGS_BY_ID";
	public static final String SQL_GET_ALL_RATINGS = "SQL_GET_ALL_RATINGS";
	
	// Type DAO
	public static final String SQL_GET_TYPES_BY_ID = "SQL_GET_TYPES_BY_ID";
	public static final String SQL_GET_ALL_TYPES = "SQL_GET_ALL_TYPES";
	
	// ModelAndMark DAO
	public static final String SQL_GET_MODELS_BY_ID = "SQL_GET_MODELS_BY_ID";
	public static final String SQL_GET_ALL_MODELS = "SQL_GET_ALL_MODELS";
	
	// Price DAO
	public static final String SQL_GET_TYPES_BY_TRANSMISSION_AND_FUEL = "SQL_GET_TYPES_BY_TRANSMISSION_AND_FUEL";
	

	public static DBSqlManager getInstance() {
		if (instance == null) {
			synchronized (DBSqlManager.class) {
				if (instance == null) {
					instance = new DBSqlManager();
					instance.resourceBundle = ResourceBundle.getBundle(FILE_NAME);
				}
			}
		}
		return instance;
	}

	public String getProperty(String key) {
		return resourceBundle.getString(key);
	}
}
