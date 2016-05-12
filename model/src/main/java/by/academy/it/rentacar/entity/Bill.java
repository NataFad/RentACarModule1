/**
 *
 */
package by.academy.it.rentacar.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

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
    private java.util.Date dateBill;
    private Order order;
    private String description;
    private BigDecimal cost;
    private int payment;
    private java.util.Date dateReturn;

    /**
     *
     */
    public Bill() {
    }

    /**
     * @return the id
     */
   // @Id
   // @GenericGenerator(name = "orderId", strategy = "foreign",
   //     parameters = )
    @Column(length = 11)
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
    @Column(name = "dateBill")
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
    @JoinColumn
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
    public java.util.Date getDateReturn() {
        return dateReturn;
    }

    /**
     * @param dateReturn the dateReturn to set
     */
    public void setDateReturn(java.sql.Date dateReturn) {
        this.dateReturn = dateReturn;
    }


}
