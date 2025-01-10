package pl.mfconsulting.java.demo.springjpa.repository.user.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "USER")
@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_ID_SEQ", initialValue = 100, allocationSize = 1)
public class User {

    protected User() {
        this.login = "";
    } // Only for JPA

    public User(String login) {
        this.login = login;
    }

    @Id
    @GeneratedValue(generator = "USER_SEQ")
    private Long id;

    public Long getId() {
        return this.id;
    }

    @Column(name = "LOGIN")
    @Size(max = 30, message = "Max size is 30!")
    private final String login;

    public String getLogin() {
        return this.login;
    }

    @Column(name = "FIRST_NAME")
    @Size(max = 50, message = "Max size is 50!")
    private String firstName;

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    @Column(name = "LAST_NAME")
    @Size(max = 70, message = "Max size is 70!")
    private String lastName;

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    @Column(name = "PASSWORD")
    @Size(max = 50, message = "Max size is 50!")
    private String password;

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String name) {
        this.password = name;
    }

    @Column(name = "EMAIL")
    @Size(max = 60, message = "Max size is 60!")
    private String email;

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Address> addresses = new ArrayList<>();
    

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", login='" + getLogin() + "'" +
                ", firstName='" + getFirstName() + "'" +
                ", lastName='" + getLastName() + "'" +
                ", password='" + getPassword() + "'" +
                ", email='" + getEmail() + "'" +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(login, user.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

}
