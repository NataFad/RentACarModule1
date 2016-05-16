/**
 * 
 */
package by.academy.it.rentacar.entity;

import by.academy.it.rentacar.enums.Transmission;

import javax.persistence.*;
import java.io.Serializable;
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
@Entity
@Table(name = "cars")
public class Car implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String registrationNumber;
	private Transmission transmission;
	private Rating rating;
	private Type type;
	private ModelAndMark model;
	private Fuel fuel;
	private Price price;
	private String description;
	private BigDecimal costOfDay;
	private BigDecimal discount;

	/**
	 * 
	 */
	public Car() {
	}

	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	@Column(name = "id", length = 11)
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
	@Column(nullable = false, length = 45)
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
	@Enumerated(EnumType.STRING)
	//@MapKeyEnumerated
	@Column(nullable = false, columnDefinition = "enum('auto','manual') default 'auto'", length = 10)
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
	 * @return the rating
	 */
	@ManyToOne
	@JoinColumn(name = "ratings_id", nullable = false)
	public Rating getRating() {
		return rating;
	}

	/**
	 * @param rating
	 *            the ratingId to set
	 */
	public void setRating(Rating rating) {
		this.rating = rating;
	}

	/**
	 * @return the type
	 */
	@ManyToOne
	@JoinColumn(name = "types_id", nullable = false)
	public Type getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(Type type) {
		this.type = type;
	}

	/**
	 * @return the modelAndMark
	 */
	@ManyToOne
	@JoinColumn(name = "ModelsAndMarks_id", nullable = false)
	public ModelAndMark getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the modelAndMark to set
	 */
	public void setModel(ModelAndMark model) {
		this.model = model;
	}

	/**
	 * @return the fuel
	 */
	@ManyToOne
	@JoinColumn(name = "Fuels_id", nullable = false)
	public Fuel getFuel() {
		return fuel;
	}

	/**
	 * @param fuel
	 *            the fuel to set
	 */
	public void setFuel(Fuel fuel) {
		this.fuel = fuel;
	}

	/**
	 * @return the price
	 */
	@ManyToOne
	@JoinColumn(name = "price_id", nullable = false)
	public Price getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(Price price) {
		this.price = price;
	}

	/**
	 * @return the description
	 */
	@Column(length = 100)
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
	@Column(nullable = false, precision = 10, scale = 2)
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
	@Column(nullable = false, precision = 6, scale = 4)
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Car car = (Car) o;

		if (id != car.id) return false;
		if (!registrationNumber.equals(car.registrationNumber)) return false;
		if (transmission != car.transmission) return false;
		if (!rating.equals(car.rating)) return false;
		if (!type.equals(car.type)) return false;
		if (!model.equals(car.model)) return false;
		if (!fuel.equals(car.fuel)) return false;
		if (!price.equals(car.price)) return false;
		if (description != null ? !description.equals(car.description) : car.description != null) return false;
		if (!costOfDay.equals(car.costOfDay)) return false;
		return discount.equals(car.discount);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + registrationNumber.hashCode();
		result = 31 * result + transmission.hashCode();
		result = 31 * result + rating.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + model.hashCode();
		result = 31 * result + fuel.hashCode();
		result = 31 * result + price.hashCode();
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + costOfDay.hashCode();
		result = 31 * result + discount.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Car{" +
				"id=" + id +
				", registrationNumber='" + registrationNumber + '\'' +
				", transmission=" + transmission +
				", rating=" + rating +
				", type=" + type +
				", model=" + model +
				", fuel=" + fuel +
				", price=" + price +
				", description='" + description + '\'' +
				", costOfDay=" + costOfDay +
				", discount=" + discount +
				'}';
	}
}
