/**
 * 
 */
package by.academy.it.rentacar.enums;

import by.academy.it.rentacar.exceptions.EnumNotFindException;

/**Enumeration Transmission
 *Enumeration Transmission: AUTO = 0, MANUAL = 1
 *
 * @author  Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public enum Transmission {
	AUTO("auto"), MANUAL("manual");

	private String transmission;

	Transmission(final String transmission){
		this.transmission = transmission;
	}

	public String getTransmission(){
		return this.transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	/**
	 * The method determines the resulting string value for the transmission
	 * 
	 * @param type
	 * @return
	 * @throws EnumNotFindException
	*/
	public static  Transmission stringToEnum(int type) throws Exception {
		switch (type) {
		case 0:
			return AUTO;
		case 1:
			return MANUAL;
		default:
			throw new EnumNotFindException("Значение <"+type+"> не входит в заданный список");
		}
	}

	public static Transmission fromValue(String value){
		Transmission transmission1 = null;
		if (value != null){
			for (Transmission transmissions : values()){
				if (transmissions.getTransmission().equals(value)){
					transmission1 = transmissions;
				}
			}
		}
		return transmission1;
	}

	public String value() {
		return name();
	}

	public String getName(){
		return this.name();
	}

	@Override
	public String toString(){
		return this.name();
	}
}
