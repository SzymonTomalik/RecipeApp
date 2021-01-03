package pl.coderslab.model;

import org.mindrot.jbcrypt.BCrypt;

public class Admins {

    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password = BCrypt.hashpw("", BCrypt.gensalt());
    private int superadmin;
    private int enable;

    public Admins() {

    }

    public Admins(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public Admins(String firstName, String lastName, String email, String password, int superadmin, int enable) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.superadmin = superadmin;
        this.enable = enable;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSuperadmin() {
        return superadmin;
    }

    public void setSuperadmin(int superadmin) {
        this.superadmin = superadmin;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public String toString() {
        return "Id: " + id + " First name: " + firstName + " Last name:" + lastName + " Email: " + email + " Password: " + password
                + " Superadmin?: " + superadmin + " Enable?: " + enable;
    }

}
