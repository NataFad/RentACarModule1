package by.academy.it.rentacar.beans;

import java.math.BigDecimal;

/**
 * Class Type
 * 
 * Class Type shows the type of the car. Depending on the type of the car price
 * is changed by a rate of the cost and is given discount.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class Type {
	
	private int id;
	private String name;
	private BigDecimal rateCost;
	private BigDecimal rateDiscount;

	/**
	 * 
	 */
	public Type() {
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the rateCost
	 */
	public BigDecimal getRateCost() {
		return rateCost;
	}

	/**
	 * @param rateCost
	 *            the rateCost to set
	 */
	public void setRatecost(BigDecimal rateCost) {
		this.rateCost = rateCost;
	}

	/**
	 * @return the rateDiscount
	 */
	public BigDecimal getRateDiscount() {
		return rateDiscount;
	}

	/**
	 * @param rateDiscount
	 *            the rateDiscount to set
	 */
	public void setRateDiscount(BigDecimal rateDiscount) {
		this.rateDiscount = rateDiscount;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Type type = (Type) o;

		if (id != type.id) return false;
		if (!name.equals(type.name)) return false;
		if (!rateCost.equals(type.rateCost)) return false;
		return rateDiscount.equals(type.rateDiscount);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + name.hashCode();
		result = 31 * result + rateCost.hashCode();
		result = 31 * result + rateDiscount.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Type{" +
				"id=" + id +
				", name='" + name + '\'' +
				", rateCost=" + rateCost +
				", rateDiscount=" + rateDiscount +
				'}';
	}
}
