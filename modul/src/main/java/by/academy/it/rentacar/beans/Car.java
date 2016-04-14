/**
 * 
 */
package by.academy.it.rentacar.beans;

import by.academy.it.rentacar.enums.Transmission;

import java.math.BigDecimal;

/**
 * Class Car contains complete information about the car with the
 * calculated amount per day and the discount.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class Car {
	private int id;
	private String registrationNumber;
	private Transmission transmission;
	private int ratingId;
	private int typeId;
	private int modelAndMarkId;
	private int fuelId;
	private int priceId;
	private String description;
	private BigDecimal costOfDay;
	private BigDecimal discount;
	private BigDecimal cost;

	/**
	 * 
	 */
	public Car() {
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
	public Transmission getTransmission() {
		return transmission;
	}

	/**
	 * @param transmission the transmission to set
	 */
	public void setTransmission(Transmission transmission) {
		this.transmission = transmission;
	}

	/**
	 * @return the ratingId
	 */
	public int getRatingId() {
		return ratingId;
	}

	/**
	 * @param ratingId
	 *            the ratingId to set
	 */
	public void setRatingId(int ratingId) {
		this.ratingId = ratingId;
	}

	/**
	 * @return the typeId
	 */
	public int getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId
	 *            the typeId to set
	 */
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the modelAndMarkId
	 */
	public int getModelAndMarkId() {
		return modelAndMarkId;
	}

	/**
	 * @param modelAndMarkId
	 *            the modelAndMarkId to set
	 */
	public void setModelAndMarkId(int modelAndMarkId) {
		this.modelAndMarkId = modelAndMarkId;
	}

	/**
	 * @return the fuelId
	 */
	public int getFuelId() {
		return fuelId;
	}

	/**
	 * @param fuelId
	 *            the fuelId to set
	 */
	public void setFuelId(int fuelId) {
		this.fuelId = fuelId;
	}

	/**
	 * @return the priceId
	 */
	public int getPriceId() {
		return priceId;
	}

	/**
	 * @param priceId
	 *            the priceId to set
	 */
	public void setPriceId(int priceId) {
		this.priceId = priceId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the costOfDay
	 */
	public BigDecimal getCostOfDay() {
		return costOfDay;
	}

	/**
	 * @param costOfDay
	 *            the costOfDay to set
	 */
	public void setCostOfDay(BigDecimal costOfDay) {
		this.costOfDay = costOfDay;
	}

	/**
	 * @return the discount
	 */
	public BigDecimal getDiscount() {
		return discount;
	}

	/**
	 * @param discount
	 *            the discount to set
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
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

		Car car = (Car) o;

		if (id != car.id) return false;
		if (ratingId != car.ratingId) return false;
		if (typeId != car.typeId) return false;
		if (modelAndMarkId != car.modelAndMarkId) return false;
		if (fuelId != car.fuelId) return false;
		if (priceId != car.priceId) return false;
		if (!registrationNumber.equals(car.registrationNumber)) return false;
		if (transmission != car.transmission) return false;
		if (description != null ? !description.equals(car.description) : car.description != null) return false;
		if (!costOfDay.equals(car.costOfDay)) return false;
		if (!discount.equals(car.discount)) return false;
		return cost.equals(car.cost);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + registrationNumber.hashCode();
		result = 31 * result + transmission.hashCode();
		result = 31 * result + ratingId;
		result = 31 * result + typeId;
		result = 31 * result + modelAndMarkId;
		result = 31 * result + fuelId;
		result = 31 * result + priceId;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + costOfDay.hashCode();
		result = 31 * result + discount.hashCode();
		result = 31 * result + cost.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Car: id=" + id + ", registrationNumber=" + registrationNumber + ", transmission=" + transmission
				+ ", ratingID=" + ratingId + ", typeId=" + typeId + ", modelAndMarkId=" + modelAndMarkId
				+ ", fuelId=" + fuelId + ", priceId=" + priceId + ", description=" + description
				+ ", costOfDay=" + costOfDay + ", discount=" + discount;
	}
	
}
