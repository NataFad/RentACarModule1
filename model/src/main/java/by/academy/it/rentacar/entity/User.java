/**
 * 
 */
package by.academy.it.rentacar.entity;

import by.academy.it.rentacar.enums.TypeUser;

import java.io.Serializable;

/***Class User
 * Class User contains complete information about the user, indicating the level of access.
 * 
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 *
 */
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String login;
	private String password;
	private TypeUser type;
	private int access;
	private String phone;
	private String name;
	private String surname;
	private String passportNumber;
	private java.util.Date passportIssue;
	private java.util.Date passportExpire;
	private String passportAuthority;
	private java.util.Date birthday;
	private String email;
	/**
	 * 
	 */
	public User() {
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
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}
	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the access
	 */
	public int getAccess() {
		return access;
	}
	/**
	 * @param access the access to set
	 */
	public void setAccess(int access) {
		this.access = access;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	/**
	 * @return the surname
	 */
	public String getSurname() {
		return surname;
	}
	/**
	 * @param surname the surname to set
	 */
	public void setSurname(String surname) {
		this.surname = surname;
	}
	/**
	 * @return the passportNumber
	 */
	public String getPassportNumber() {
		return passportNumber;
	}
	/**
	 * @param passportNumber the passportNumber to set
	 */
	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}
	/**
	 * @return the passportIssue
	 */
	public java.util.Date getPassportIssue() {
		return passportIssue;
	}
	/**
	 * @param date the passportIssue to set
	 */
	public void setPassportIssue(java.util.Date date) {
		this.passportIssue = date;
	}
	/**
	 * @return the passportExpire
	 */
	public java.util.Date getPassportExpire() {
		return passportExpire;
	}
	/**
	 * @param passportExpire the passportExpire to set
	 */
	public void setPassportExpire(java.util.Date passportExpire) {
		this.passportExpire = passportExpire;
	}
	/**
	 * @return the passportAuthority
	 */
	public String getPassportAuthority() {
		return passportAuthority;
	}
	/**
	 * @param passportAuthority the passportAuthority to set
	 */
	public void setPassportAuthority(String passportAuthority) {
		this.passportAuthority = passportAuthority;
	}
	/**
	 * @return the birthday
	 */
	public java.util.Date getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(java.util.Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	/**
	 * @return the type
	 */
	public TypeUser getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(TypeUser type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (id != user.id) return false;
		if (access != user.access) return false;
		if (!login.equals(user.login)) return false;
		if (!password.equals(user.password)) return false;
		if (type != user.type) return false;
		if (!phone.equals(user.phone)) return false;
		if (!name.equals(user.name)) return false;
		if (!surname.equals(user.surname)) return false;
		if (passportNumber != null ? !passportNumber.equals(user.passportNumber) : user.passportNumber != null)
			return false;
		if (passportIssue != null ? !passportIssue.equals(user.passportIssue) : user.passportIssue != null)
			return false;
		if (passportExpire != null ? !passportExpire.equals(user.passportExpire) : user.passportExpire != null)
			return false;
		if (passportAuthority != null ? !passportAuthority.equals(user.passportAuthority) : user.passportAuthority != null)
			return false;
		if (!birthday.equals(user.birthday)) return false;
		return email.equals(user.email);

	}

	@Override
	public int hashCode() {
		int result = id;
		result = 31 * result + login.hashCode();
		result = 31 * result + password.hashCode();
		result = 31 * result + type.hashCode();
		result = 31 * result + access;
		result = 31 * result + phone.hashCode();
		result = 31 * result + name.hashCode();
		result = 31 * result + surname.hashCode();
		result = 31 * result + (passportNumber != null ? passportNumber.hashCode() : 0);
		result = 31 * result + (passportIssue != null ? passportIssue.hashCode() : 0);
		result = 31 * result + (passportExpire != null ? passportExpire.hashCode() : 0);
		result = 31 * result + (passportAuthority != null ? passportAuthority.hashCode() : 0);
		result = 31 * result + birthday.hashCode();
		result = 31 * result + email.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "User: id=" + id + ", login=" + login + ", password=" + password + ", access=" + access + ", type=" + type 
				+ ", name=" + name + ", surname=" + surname + ", phone=" + phone + ", birthday=" + birthday + ", email=" + email
				+ ", passportNumber=" + passportNumber + ", passportIssue=" + passportIssue 
				+ ", passportExpire=" + passportExpire + ", passportAuthority=" + passportAuthority;
	}  
}
