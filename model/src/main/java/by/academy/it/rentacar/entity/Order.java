/**
 *
 */
package by.academy.it.rentacar.entity;

import by.academy.it.rentacar.enums.StatusOrder;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * Class Order
 * Class Order contains complete information about the order with the calculated amount per rent and his status.
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
@Entity
@Table(name = "orders")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private Date dateOrder;
    private User user;
    private Car car;
    private Date fromDate;
    private Date byDate;
    private String placeOfReceipt;
    private String placeOfReturn;
    private BigDecimal cost;
    private StatusOrder status;
    private String note;
    private String descriptionReason;
    private Bill bill;

    /**
     *
     */
    public Order() {
    }

    /**
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
     * @return the dateOrder
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "dateorder", nullable = false)
    public Date getDateOrder() {
        return dateOrder;
    }

    /**
     * @param dateOrder the dateOrder to set
     */
    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }

    /**
     * @return the user
     */
    @ManyToOne
    @JoinColumn(name = "users_id", nullable = false)
    public User getUser() {
        return user;
    }

    /**
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the car
     */
    @ManyToOne
    @JoinColumn(name = "cars_id", nullable = false)
    public Car getCar() {
        return car;
    }

    /**
     * @param car
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * @return the fromDate
     */
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public Date getFromDate() {
        return fromDate;
    }

    /**
     * @param fromDate the fromDate to set
     */
    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    /**
     * @return the byDate
     */
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    public Date getByDate() {
        return byDate;
    }

    /**
     * @param byDate the byDate to set
     */
    public void setByDate(Date byDate) {
        this.byDate = byDate;
    }

    /**
     * @return the placeOfReceipt
     */
    @Column(length = 45, nullable = false)
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
    @Column(length = 45, nullable = false)
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
     * @return the status
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10,
            columnDefinition = "enum('AWAITING','APPROVED', 'REJECTED', 'CLOSED') default 'AWAITING'")
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
    @Column(length = 45)
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
    @Column(length = 45)
    public String getDescriptionReason() {
        return descriptionReason;
    }

    /**
     * @param descriptionReason the descriptionReason to set
     */
    public void setDescriptionReason(String descriptionReason) {
        this.descriptionReason = descriptionReason;
    }

    /**
     * @return the bill
     */
    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    public Bill getBill() {
        return bill;
    }

    /**
     * @param bill
     */
    public void setBill(Bill bill) {
        this.bill = bill;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (id != order.id) return false;
        if (!dateOrder.equals(order.dateOrder)) return false;
        if (!user.equals(order.user)) return false;
        if (!car.equals(order.car)) return false;
        if (!fromDate.equals(order.fromDate)) return false;
        if (!byDate.equals(order.byDate)) return false;
        if (!placeOfReceipt.equals(order.placeOfReceipt)) return false;
        if (!placeOfReturn.equals(order.placeOfReturn)) return false;
        if (!cost.equals(order.cost)) return false;
        if (status != order.status) return false;
        if (note != null ? !note.equals(order.note) : order.note != null) return false;
        if (descriptionReason != null ? !descriptionReason.equals(order.descriptionReason) : order.descriptionReason != null)
            return false;
        return bill != null ? bill.equals(order.bill) : order.bill == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + dateOrder.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + car.hashCode();
        result = 31 * result + fromDate.hashCode();
        result = 31 * result + byDate.hashCode();
        result = 31 * result + placeOfReceipt.hashCode();
        result = 31 * result + placeOfReturn.hashCode();
        result = 31 * result + cost.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + (note != null ? note.hashCode() : 0);
        result = 31 * result + (descriptionReason != null ? descriptionReason.hashCode() : 0);
        result = 31 * result + (bill != null ? bill.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", dateOrder=" + dateOrder +
                ", user=" + user +
                ", car=" + car +
                ", fromDate=" + fromDate +
                ", byDate=" + byDate +
                ", placeOfReceipt='" + placeOfReceipt + '\'' +
                ", placeOfReturn='" + placeOfReturn + '\'' +
                ", cost=" + cost +
                ", status=" + status +
                ", note='" + note + '\'' +
                ", descriptionReason='" + descriptionReason + '\'' +
                ", bill=" + bill +
                '}';
    }
}
