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
	AUTO, MANUAL;
	/**
	 * The method determines the resulting string value for the transmission
	 * 
	 * @param typeInt
	 * @return
	 * @throws EnumNotFindException
	 */
	public static final Transmission stringToEnum(int typeInt) throws Exception {
		switch (typeInt) {
		case 0:
			return AUTO;
		case 1:
			return MANUAL;
		default:
			throw new EnumNotFindException("Значение <"+typeInt+"> не входит в заданный список");
		}
	}
	public String value() {
		return name();
	}

	public static Transmission fromValue(String v) {
		return valueOf(v);
	}
}
