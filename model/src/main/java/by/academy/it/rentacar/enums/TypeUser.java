package by.academy.it.rentacar.enums;

import by.academy.it.rentacar.exceptions.EnumNotFindException;

/**Enumeration TypeUser
 *
 * Enumeration TypeUser: GUEST=0, USER=1, ADMINISTRATOR=2
 *
 * @author  Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public enum TypeUser{
	GUEST, USER, ADMINISTRATOR;
	/**
	 * The method determines the resulting string value for the type of user
	 * 
	 * @param typeInt
	 * @return
	 * @throws EnumNotFindException
	 */
	public static final TypeUser stringToEnum(int typeInt) throws Exception {
		switch (typeInt) {
		case 0:
			return GUEST;
		case 1:
			return USER;
		case 2:
			return ADMINISTRATOR;
		default:
			throw new EnumNotFindException("Значение <"+typeInt+"> не входит в заданный список");
		}
	}

	public String value() {
		return name();
	}

	public static TypeUser fromValue(String v) {
		return valueOf(v);
	}
}