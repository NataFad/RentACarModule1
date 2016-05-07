/**
 * 
 */
package by.academy.it.rentacar.entity;

import by.academy.it.rentacar.enums.Transmission;

import java.math.BigDecimal;

/**Class Price
 * Class Price shows the cost and discount of the car. 
 * The cost and discount is depending on the the transmission and the fuel
 * 
 * @author  Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class Price {
	private int id;
	private String name;
	private Transmission transmission;
	private int fuelId;
	private BigDecimal costOfDay;
	private BigDecimal discount;
	/**
	 * 
	 */
	public Price() {
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @return the fuelId
	 */
	public int getFuelId() {
		return fuelId;
	}
	/**
	 * @param fuelId the fuelId to set
	 */
	public void setFuelId(int fuelId) {
		this.fuelId = fuelId;
	}
	/**
	 * @return the costOfDay
	 */
	public BigDecimal getCostOfDay() {
		return costOfDay;
	}
	/**
	 * @param costOfDay the costofday to set
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
	 * @param discount the discount to set
	 */
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Price price = (Price) o;

		if (id != price.id) return false;
		if (fuelId != price.fuelId) return false;
		if (!name.equals(price.name)) return false;
		if (transmission != price.transmission) return false;
		if (!costOfDay.equals(price.costOfDay)) return false;
		return discount != null ? discount.equals(price.discount) : price.discount == null;

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + name.hashCode();
		result = 31 * result + transmission.hashCode();
		result = 31 * result + fuelId;
		result = 31 * result + costOfDay.hashCode();
		result = 31 * result + (discount != null ? discount.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Price{" +
				"id=" + id +
				", name='" + name + '\'' +
				", transmission=" + transmission +
				", fuelId=" + fuelId +
				", costOfDay=" + costOfDay +
				", discount=" + discount +
				'}';
	}
}
