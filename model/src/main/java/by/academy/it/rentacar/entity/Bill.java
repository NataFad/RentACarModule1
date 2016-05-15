/**
 *
 */
package by.academy.it.rentacar.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Class Bill
 * Class Bill contains complete information about the bill of repairs.
 * <p>
 * Class Bill: id, dateBill, orderId, description, cost, payment, dateReturn.
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
@Entity
@Table(name = "bills")
public class Bill implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Date dateBill;
    private Order order;
    private String description;
    private BigDecimal cost;
    private int payment;
    private Date dateReturn;

    /**
     *
     */
    public Bill() {
    }

    /**
     * @return the orders_id
     */
    @Id
    @GenericGenerator(name = "orders_id", strategy = "foreign",
            parameters = @org.hibernate.annotations.Parameter(name = "property", value = "order"))
    @GeneratedValue(generator = "orders_id")
    @Column(name = "orders_id", length = 11)
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
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public java.util.Date getDateBill() {
        return dateBill;
    }

    /**
     * @param dateBill the dateBill to set
     */
    public void setDateBill(java.util.Date dateBill) {
        this.dateBill = dateBill;
    }

    /**
     * @return the order
     */
    @OneToOne
    @PrimaryKeyJoinColumn
    public Order getOrder() {
        return order;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrder(Order orderId) {
        this.order = order;
    }

    /**
     * @return the description
     */
    @Column(nullable = false, length = 50)
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
    @Column(nullable = false, precision = 10, scale = 2)
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
    @Column(length = 1)
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
    @Temporal(TemporalType.DATE)
    @Column
    public Date getDateReturn() {
        return dateReturn;
    }

    /**
     * @param dateReturn the dateReturn to set
     */
    public void setDateReturn(Date dateReturn) {
        this.dateReturn = dateReturn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bill bill = (Bill) o;

        if (id != bill.id) return false;
        if (payment != bill.payment) return false;
        if (!dateBill.equals(bill.dateBill)) return false;
        if (!order.equals(bill.order)) return false;
        if (!description.equals(bill.description)) return false;
        if (!cost.equals(bill.cost)) return false;
        return dateReturn != null ? dateReturn.equals(bill.dateReturn) : bill.dateReturn == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + dateBill.hashCode();
        result = 31 * result + order.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + cost.hashCode();
        result = 31 * result + payment;
        result = 31 * result + (dateReturn != null ? dateReturn.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Bill{" +
                "id=" + id +
                ", dateBill=" + dateBill +
                ", order=" + order +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", payment=" + payment +
                ", dateReturn=" + dateReturn +
                '}';
    }
}
