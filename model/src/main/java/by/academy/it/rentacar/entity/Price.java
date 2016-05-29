/**
 *
 */
package by.academy.it.rentacar.entity;

import by.academy.it.rentacar.enums.Transmission;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class Price
 * Class Price shows the cost and discount of the car.
 * The cost and discount is depending on the the transmission and the fuel
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 */
@Entity
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "price")
@Table(name = "price", uniqueConstraints = @UniqueConstraint(name = "search", columnNames = {"transmission", "Fuels_id"}))
public class Price implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private Transmission transmission;
    private Fuel fuel;
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
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11)
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
    @Column(name = "name", nullable = false, length = 45)
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
    @Enumerated(EnumType.STRING)
    @Column(name = "transmission", nullable = false, columnDefinition = "enum('AUTO','MANUAL') DEFAULT 'AUTO'", length = 10)
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
     * @return the fuel
     */
    @ManyToOne
    @JoinColumn(name = "Fuels_id")
    public Fuel getFuel() {
        return fuel;
    }

    /**
     * @param fuel the fuelId to set
     */
    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    /**
     * @return the costOfDay
     */
    @Column(name = "costOfDay", nullable = false, precision = 10, scale = 2)
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
    @Column(name = "discount", nullable = false, precision = 3, scale = 2)
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
        if (!name.equals(price.name)) return false;
        if (transmission != price.transmission) return false;
        if (!fuel.equals(price.fuel)) return false;
        if (!costOfDay.equals(price.costOfDay)) return false;
        return discount.equals(price.discount);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + transmission.hashCode();
        result = 31 * result + fuel.hashCode();
        result = 31 * result + costOfDay.hashCode();
        result = 31 * result + discount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", transmission=" + transmission +
                ", fuel=" + fuel +
                ", costOfDay=" + costOfDay +
                ", discount=" + discount +
                '}';
    }
}
