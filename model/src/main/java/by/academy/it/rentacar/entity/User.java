/**
 *
 */
package by.academy.it.rentacar.entity;

import by.academy.it.rentacar.enums.TypeUser;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;

/***
 * Class User
 * Class User contains complete information about the user, indicating the level of access.
 *
 * @author Fadeeva Natallia
 * @version 1.1
 * @since 2016-04
 */
@Entity
@Table(name = "users",  uniqueConstraints =  @UniqueConstraint(name = "login", columnNames = "login"))
@Component
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    private int id;
    private String login;
    private String password;
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
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(length = 11)
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
    @Column(nullable = false, length = 10)
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
    @Column(nullable = false, length = 45)
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
    @Column(length = 3, nullable = false)
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
    @Column(length = 45)
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
    @Column(length = 45, nullable = false)
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
    @Column(length = 45, nullable = false)
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
    @Column(length = 9)
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
    @Temporal(TemporalType.DATE)
    @Column
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
    @Temporal(TemporalType.DATE)
    @Column
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
    @Column(length = 45)
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
    @Temporal(TemporalType.DATE)
    @Column
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
    @Column(length = 45, nullable = false)
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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
        if (birthday.equals(user.birthday)) if (email.equals(user.email)) return true;
        return false;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + login.hashCode();
        result = 31 * result + password.hashCode();
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
        TypeUser typeUser = null;
        try {
            typeUser = TypeUser.stringToEnum(getAccess());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "User: id=" + id + ", login=" + login + ", password=" + password + ", access=" + access
                + ", type=" + typeUser + ", name=" + name + ", surname=" + surname + ", phone=" + phone
                + ", birthday=" + birthday + ", email=" + email + ", passportNumber=" + passportNumber
                + ", passportIssue=" + passportIssue + ", passportExpire=" + passportExpire
                + ", passportAuthority=" + passportAuthority;
    }
}
