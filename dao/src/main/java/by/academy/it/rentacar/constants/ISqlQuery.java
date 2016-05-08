
package by.academy.it.rentacar.constants;

/**Interface ISqlQuery
 * initializes constants of sql-queries
 * 
 * @author Fadeeva Natallia
 * @version 1.0
 * @since 2016-03
 */
public interface ISqlQuery {

	// USER DAO
	String ADD_USER = "INSERT INTO users(name, surname, login, password, access, phone, passportNumber, " +
					"passportIssue, passportExpire, passportAuthority, birthday, email) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String UPDATE_USER = " UPDATE users " +
					"SET passportNumber = ?, passportIssue = ?, passportExpire = ?, passportAuthority = ? " +
					"WHERE id = ?";
	String UPDATE_ACCESS = "UPDATE users SET access = ? WHERE id = ?";
	String GET_ACCESS = "SELECT access FROM users WHERE id = ?";
	String CHECK_LOGIN = "SELECT id FROM users WHERE login = ?";
	String GET_USER = "SELECT * FROM users WHERE login = ? AND password = ?";
	String GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	String COUNT_USERS = "SELECT COUNT(*) FROM users";
	String GET_ALL_USERS = "SELECT * FROM users ORDER BY id";
	String DELETE_USER = "DELETE FROM users WHERE id = ?";

	// CAR DAO
	String GET_ALL_CARS = "SELECT cars.*, ratings.name as rating, fuels.name as fuel, " +
					"types.name as type_name, modelsandmarks.mark as marka, modelsandmarks.model as model  " +
					"FROM cars " +
					"left join ratings on ratings.id = cars.ratings_id " +
					"left join types on types.id = cars.types_id " +
					"left join modelsandmarks on modelsandmarks.id = cars.modelsandmarks_id  " +
					"left join fuels on fuels.id = cars.fuels_id " +
					"ORDER BY cars.id";
	String ADD_CAR = " INSERT INTO " +
					"cars(registrationNumber, transmission, ratings_id, types_id, modelsAndMarks_id, " +
					"price_id, fuels_id, costOfDay, discount, description) " +
					"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	String DELETE_CAR = "DELETE FROM cars WHERE id = ?";

	// Price DAO
	String GET_PRICE_BY_TRANSMISSION_AND_FUEL = "SELECT * FROM price WHERE transmission = ? AND fuels_id = ?";
	String ADD_PRICE = "INSERT INTO price (name, transmission, Fuels_id, costofday, discount) VALUES (?,?,?,?,?)";
	String DELETE_PRICE = "DELETE FROM price WHERE id = ?";
}
