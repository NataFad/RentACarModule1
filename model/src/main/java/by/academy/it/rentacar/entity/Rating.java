/**
 *
 */
package by.academy.it.rentacar.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Class Rating
 * <p>
 * Class Rating indicates the condition of the car.
 * Depending on the rating of the car price is changed by a rate of the cost
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 */
@Entity
@Table(name = "ratings")
public class Rating implements Serializable{
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;
    private BigDecimal rateCost;

    /**
     *
     */
    public Rating() {
    }

    /**
     *
     */
    public Rating(String name, BigDecimal rateCost) {
        this.name = name;
        this.rateCost = rateCost;
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
     * @return the rateCost
     */
    @Column(name = "ratecost", nullable = false, precision = 3, scale = 2)
    public BigDecimal getRateCost() {
        return rateCost;
    }

    /**
     * @param rateCost the ratecost to set
     */
    public void setRateCost(BigDecimal rateCost) {
        this.rateCost = rateCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rating rating = (Rating) o;

        if (id != rating.id) return false;
        if (!name.equals(rating.name)) return false;
        return rateCost.equals(rating.rateCost);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + rateCost.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rateCost=" + rateCost +
                '}';
    }
}
