/**
 * 
 */
package by.academy.it.rentacar.dao;

import by.academy.it.rentacar.managers.DBSqlManager;

/**Abstract class DAO
 * contains: DBConnectionPool poolInstance; DBSqlManager sqlManager.
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public abstract class DAO implements IDAO {
    protected static DBSqlManager sqlManager;
    
    protected DAO() {
        sqlManager = DBSqlManager.getInstance();
    }

    /**
     * Method getCurrentSQLDate()
     * translate the format util of the date in the format sql
     *
     * @param date
     * @return
     */
    protected static java.sql.Date getCurrentSQLDate(java.util.Date date) {
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		return sqlDate;
	}

    /**
     * Method getCurrentDate()
     * translate the format sql of the date in the format util
     *
     * @param sqlDate
     * @return
     */
    protected static java.util.Date getCurrentDate(java.sql.Date sqlDate) {
		java.util.Date date = new java.util.Date(sqlDate.getTime());
		return date;
	}

}
