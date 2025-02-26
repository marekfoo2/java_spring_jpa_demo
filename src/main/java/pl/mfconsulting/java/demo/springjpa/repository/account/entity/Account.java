package pl.mfconsulting.java.demo.springjpa.repository.account.entity;

import java.util.*;

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
@Table(name = "ACCOUNT")
@SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "ACCOUNT_ID_SEQ", initialValue = 100, allocationSize = 1)
public class Account {

    protected Account() {
        this.login = "";
    } // Only for JPA

    public Account(String login) {
        this.login = login;
    }

    @Id
    @GeneratedValue(generator = "ACCOUNT_SEQ")
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
        mappedBy = "account",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Address> addresses = new HashSet<>();
    

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
        if (!(o instanceof Account)) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(login, account.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(login);
    }

    public Set<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(Set<Address> addresses) {
        this.addresses = addresses;
    }
}
