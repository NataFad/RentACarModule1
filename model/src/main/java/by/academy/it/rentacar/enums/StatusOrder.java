package by.academy.it.rentacar.enums;

import by.academy.it.rentacar.exceptions.EnumNotFindException;

/**Enumeration StatusOrder
 * Enumeration StatusOrder: AWAITING=0, APPROVED=1, REJECTED=2, CLOSED=3
 * 
 * @author  Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public enum StatusOrder {
	 AWAITING("AWAITING"), APPROVED("APPROVED"), REJECTED("REJECTED"), CLOSED("CLOSED");
	 private String status;

	StatusOrder(final String status) {
		this.status = status;
	}

	public String getValue() {
		return status;
	}

	/**
	 * The method determines the resulting string value for the status of order
	 * 
	 * @param statusInt
	 * @return
	 * @throws EnumNotFindException
	 */
	public static final StatusOrder stringToEnum(int statusInt) throws Exception {
		switch (statusInt) {
		case 0:
			return AWAITING;
		case 1:
			return APPROVED;
		case 2:
			return REJECTED;
		case 3:
			return CLOSED;
		default:
			throw new EnumNotFindException("Значение <"+statusInt+"> не входит в заданный список");
		}
	}
	public static StatusOrder fromValue(String value){
		StatusOrder statusOrder = null;
		if (value != null){
			for (StatusOrder statusOrder1 : values()){
				if (statusOrder1.getValue().equals(value)){
					statusOrder = statusOrder1;
				}
			}
		}
		return statusOrder;
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
