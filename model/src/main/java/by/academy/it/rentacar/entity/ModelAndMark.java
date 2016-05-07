/**
 *
 */
package by.academy.it.rentacar.entity;

import javax.persistence.*;

/**
 * Class ModelAndMark: id, model, mark
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
@Entity
@Table(name = "modelsandmarks")
public class ModelAndMark {
    private int id;
    private String mark;
    private String model;

    /**
     *
     */
    public ModelAndMark() {
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
     * @return the model
     */
    @Column(name = "model", nullable = false, length = 45)
    public String getModel() {
        return model;
    }

    /**
     * @param model the model to set
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return the mark
     */
    @Column(name = "mark", nullable =  false, length = 45)
    public String getMark() {
        return mark;
    }

    /**
     * @param mark the mark to set
     */
    public void setMark(String mark) {
        this.mark = mark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ModelAndMark that = (ModelAndMark) o;

        if (id != that.id) return false;
        if (!mark.equals(that.mark)) return false;
        return model.equals(that.model);

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + mark.hashCode();
        result = 31 * result + model.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ModelAndMark{" +
                "id=" + id +
                ", mark='" + mark + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
