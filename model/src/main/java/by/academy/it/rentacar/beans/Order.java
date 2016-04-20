/**
 * 
 */
package by.academy.it.rentacar.beans;

import by.academy.it.rentacar.enums.StatusOrder;

import java.math.BigDecimal;


/**Class Order
 * Class Order contains complete information about the order with the calculated amount per rent and his status.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class Order {
	private int id;
	private java.sql.Date dateOrder;
	private int userId;
	private int carId;
	private java.sql.Date fromDate;
	private java.sql.Date byDate;
	private String placeOfReceipt;
	private String placeOfReturn;
	private BigDecimal cost;
	private StatusOrder status;
	private String note;
	private String descriptionReason;
	/**
	 * 
	 */
	public Order() {
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
	 * @return the dateOrder
	 */
	public java.sql.Date getDateOrder() {
		return dateOrder;
	}
	/**
	 * @param dateOrder the dateOrder to set
	 */
	public void setDateOrder(java.sql.Date dateOrder) {
		this.dateOrder = dateOrder;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the carId
	 */
	public int getCarId() {
		return carId;
	}
	/**
	 * @param carId the carId to set
	 */
	public void setCarId(int carId) {
		this.carId = carId;
	}
	/**
	 * @return the fromDate
	 */
	public java.sql.Date getFromDate() {
		return fromDate;
	}
	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(java.sql.Date fromDate) {
		this.fromDate = fromDate;
	}
	/**
	 * @return the byDate
	 */
	public java.sql.Date getByDate() {
		return byDate;
	}
	/**
	 * @param byDate the byDate to set
	 */
	public void setByDate(java.sql.Date byDate) {
		this.byDate = byDate;
	}
	/**
	 * @return the placeOfReceipt
	 */
	public String getPlaceOfReceipt() {
		return placeOfReceipt;
	}
	/**
	 * @param placeOfReceipt the placeOfReceipt to set
	 */
	public void setPlaceOfReceipt(String placeOfReceipt) {
		this.placeOfReceipt = placeOfReceipt;
	}
	/**
	 * @return the placeOfReturn
	 */
	public String getPlaceOfReturn() {
		return placeOfReturn;
	}
	/**
	 * @param placeOfReturn the placeOfReturn to set
	 */
	public void setPlaceOfReturn(String placeOfReturn) {
		this.placeOfReturn = placeOfReturn;
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
	/**
	 * @return the status
	 */
	public StatusOrder getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusOrder status) {
		this.status = status;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}
	/**
	 * @return the descriptionReason
	 */
	public String getDescriptionReason() {
		return descriptionReason;
	}
	/**
	 * @param descriptionReason the descriptionReason to set
	 */
	public void setDescriptionReason(String descriptionReason) {
		this.descriptionReason = descriptionReason;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Order order = (Order) o;

		if (id != order.id) return false;
		if (userId != order.userId) return false;
		if (carId != order.carId) return false;
		if (!dateOrder.equals(order.dateOrder)) return false;
		if (!fromDate.equals(order.fromDate)) return false;
		if (!byDate.equals(order.byDate)) return false;
		if (!placeOfReceipt.equals(order.placeOfReceipt)) return false;
		if (!placeOfReturn.equals(order.placeOfReturn)) return false;
		if (!cost.equals(order.cost)) return false;
		if (status != order.status) return false;
		if (note != null ? !note.equals(order.note) : order.note != null) return false;
		return descriptionReason != null ? descriptionReason.equals(order.descriptionReason) : order.descriptionReason == null;

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + dateOrder.hashCode();
		result = 31 * result + userId;
		result = 31 * result + carId;
		result = 31 * result + fromDate.hashCode();
		result = 31 * result + byDate.hashCode();
		result = 31 * result + placeOfReceipt.hashCode();
		result = 31 * result + placeOfReturn.hashCode();
		result = 31 * result + cost.hashCode();
		result = 31 * result + status.hashCode();
		result = 31 * result + (note != null ? note.hashCode() : 0);
		result = 31 * result + (descriptionReason != null ? descriptionReason.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Order{" +
				"id=" + id +
				", dateOrder=" + dateOrder +
				", userId=" + userId +
				", carId=" + carId +
				", fromDate=" + fromDate +
				", byDate=" + byDate +
				", placeOfReceipt='" + placeOfReceipt + '\'' +
				", placeOfReturn='" + placeOfReturn + '\'' +
				", cost=" + cost +
				", status=" + status +
				", note='" + note + '\'' +
				", descriptionReason='" + descriptionReason + '\'' +
				'}';
	}
}
