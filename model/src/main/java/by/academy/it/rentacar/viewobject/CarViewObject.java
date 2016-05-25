/**
 * 
 */
package by.academy.it.rentacar.viewobject;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class CarViewObject contains complete information about the car with the
 * calculated amount per days of the rent for the browser.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-05
 *
 */
public class CarViewObject implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String registrationNumber;
	private String transmission;
	private String rating;
	private String typeCar;
	private String model;
	private String marka;
	private String fuel;
	private String description;
	private BigDecimal cost;

	/**
	 *
	 */
	public CarViewObject() {
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the registrationNumber
	 */

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	/**
	 * @param registrationNumber
	 *            the registrationNumber to set
	 */
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	/**
	 * @return the transmission
	 */
	public String getTransmission() {
		return transmission;
	}

	/**
	 * @param transmission the transmission to set
	 */
	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	/**
	 * @return the rating
	 */
	public String getRating() {
		return rating;
	}

	/**
	 * @param rating
	 */
	public void setRating(String rating) {
		this.rating = rating;
	}

	/**
	 * @return the typeCar
	 */
	public String getType() {
		return typeCar;
	}

	/**
	 * @param typeCar
	 */
	public void setType(String typeCar) {
		this.typeCar = typeCar;
	}

	/**
	 * @return the model from the modelAndMark
	 */
	public String getModel() {
		return model;
	}

	/**
	 * @param model
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the marka from the modelAndMark
	 */
	public String getMarka() {
		return marka;
	}

	/**
	 * @param marka
	 */
	public void setMarka(String marka) {
		this.marka = marka;
	}

	/**
	 * @return the fuel
	 */
	public String getFuel() {
		return fuel;
	}

	/**
	 * @param fuel
	 */
	public void setFuel(String fuel) {
		this.fuel = fuel;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the cost
	 */
	public BigDecimal getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CarViewObject that = (CarViewObject) o;

		if (id != that.id) return false;
		if (!registrationNumber.equals(that.registrationNumber)) return false;
		if (transmission.equals(that.transmission)) return false;
		if (!rating.equals(that.rating)) return false;
		if (!typeCar.equals(that.typeCar)) return false;
		if (!model.equals(that.model)) return false;
		if (!marka.equals(that.marka)) return false;
		if (!fuel.equals(that.fuel)) return false;
		if (description != null ? !description.equals(that.description) : that.description != null) return false;
		return cost.equals(that.cost);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + registrationNumber.hashCode();
		result = 31 * result + transmission.hashCode();
		result = 31 * result + rating.hashCode();
		result = 31 * result + typeCar.hashCode();
		result = 31 * result + model.hashCode();
		result = 31 * result + marka.hashCode();
		result = 31 * result + fuel.hashCode();
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + cost.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "CarViewObject{" +
				"id=" + id +
				", registrationNumber='" + registrationNumber + '\'' +
				", transmission=" + transmission +
				", rating='" + rating + '\'' +
				", type='" + typeCar + '\'' +
				", model='" + model + '\'' +
				", marka='" + marka + '\'' +
				", fuel='" + fuel + '\'' +
				", description='" + description + '\'' +
				", cost=" + cost +
				'}';
	}
}
