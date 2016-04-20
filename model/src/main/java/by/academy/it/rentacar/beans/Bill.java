/**
 * 
 */
package by.academy.it.rentacar.beans;

import java.math.BigDecimal;

/**Class Bill
 * Class Bill contains complete information about the bill of repairs.
 *
 * Class Bill: id, dateBill, orderId, description, cost, payment, dateReturn.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class Bill {
	private int id;
	private java.sql.Date dateBill;
	private int orderId;
	private String description;
	private BigDecimal cost;
	private int payment;
	private java.sql.Date dateReturn;
	/**
	 * 
	 */
	public Bill() {
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
	 * @return the dateBill
	 */
	public java.sql.Date getDateBill() {
		return dateBill;
	}
	/**
	 * @param dateBill the dateBill to set
	 */
	public void setDateBill(java.sql.Date dateBill) {
		this.dateBill = dateBill;
	}
	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}
	/**
	 * @param orderId the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
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
	/**
	 * @return the payment
	 */
	public int getPayment() {
		return payment;
	}
	/**
	 * @param payment the payment to set
	 */
	public void setPayment(int payment) {
		this.payment = payment;
	}
	/**
	 * @return the dateReturn
	 */
	public java.sql.Date getDateReturn() {
		return dateReturn;
	}
	/**
	 * @param dateReturn the dateReturn to set
	 */
	public void setDateReturn(java.sql.Date dateReturn) {
		this.dateReturn = dateReturn;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Bill bill = (Bill) o;

		if (id != bill.id) return false;
		if (orderId != bill.orderId) return false;
		if (payment != bill.payment) return false;
		if (dateBill != null ? !dateBill.equals(bill.dateBill) : bill.dateBill != null) return false;
		if (description != null ? !description.equals(bill.description) : bill.description != null) return false;
		if (cost != null ? !cost.equals(bill.cost) : bill.cost != null) return false;
		return dateReturn != null ? dateReturn.equals(bill.dateReturn) : bill.dateReturn == null;

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + (dateBill != null ? dateBill.hashCode() : 0);
		result = 31 * result + orderId;
		result = 31 * result + (description != null ? description.hashCode() : 0);
		result = 31 * result + (cost != null ? cost.hashCode() : 0);
		result = 31 * result + payment;
		result = 31 * result + (dateReturn != null ? dateReturn.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Bill{" +
				"id=" + id +
				", dateBill=" + dateBill +
				", orderId=" + orderId +
				", description='" + description + '\'' +
				", cost=" + cost +
				", payment=" + payment +
				", dateReturn=" + dateReturn +
				'}';
	}
}
