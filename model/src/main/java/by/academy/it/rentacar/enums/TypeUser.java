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
	GUEST(0), USER(1), ADMINISTRATOR(2);

	private int type;

	TypeUser(final int type){
		this.type = type;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

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

	public static TypeUser fromValue(int value){
		TypeUser typeUser = null;
		if (value != 0){
			for (TypeUser typeUsers : values()){
				if (typeUsers.getType() == value){
					typeUser = typeUsers;
				}
			}
		}
		return typeUser;
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