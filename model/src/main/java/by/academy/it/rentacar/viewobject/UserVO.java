package by.academy.it.rentacar.viewobject;

public class UserVO {
    private int id;
    private int access;
    private String firstname;
    private String surname;
    private String login;
    private String password;

    public UserVO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccess() {
        return access;
    }

    public void setAccess(int access) {
        this.access = access;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserVO userVO = (UserVO) o;

        if (id != userVO.id) return false;
        if (access != userVO.access) return false;
        if (!firstname.equals(userVO.firstname)) return false;
        if (surname != null ? !surname.equals(userVO.surname) : userVO.surname != null) return false;
        if (login != null ? !login.equals(userVO.login) : userVO.login != null) return false;
        return password != null ? password.equals(userVO.password) : userVO.password == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + firstname.hashCode();
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + access;
        return result;
    }

    @Override
    public String toString() {
        return "UserVO{" +
                "id=" + id +
                ", access=" + access +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}


