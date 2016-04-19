
package by.academy.it.rentacar.managers;

import java.util.ResourceBundle;

/**Class DBSqlManager
 * 
 * Class DBSqlManager provides access to prepared statements, which contains in sql.properties file.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class DBSqlManager {
	private volatile static DBSqlManager instance;
	private static ResourceBundle resourceBundle;

	public static final String FILE_NAME = "sql";

	@SuppressWarnings("static-access")
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
