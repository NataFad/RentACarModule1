/**
 * 
 */
package by.academy.it.rentacar.beans;

/**
 * Class Fuel: id, name
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 * 
 */
public class Fuel {
	 private int id;
	 private String name;
	/**
	 * 
	 */
	public Fuel() {
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
	 * @return the name
	 */
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
