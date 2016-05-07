/**
 *
 */
package by.academy.it.rentacar.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Class Fuel: id, name
 *
 * @author Fadeeva Natallia
 * @version 1.2
 * @since 2016-05
 */
@Entity
@Table(name = "fuels")
public class Fuel implements Serializable {
    private static final long serialVersionUID = 1L;
    private int id;
    private String name;

    /**
     *
     */
    public Fuel() {
    }

    public Fuel(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Fuel fuel = (Fuel) o;

        if (id != fuel.id) return false;
        return name.equals(fuel.name);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Fuel: " + id + ". " + name;
    }
}
