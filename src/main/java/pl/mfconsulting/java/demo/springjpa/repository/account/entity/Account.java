package pl.mfconsulting.java.demo.springjpa.repository.account.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "ACCOUNT")
@SequenceGenerator(name = "ACCOUNT_SEQ", sequenceName = "ACCOUNT_ID_SEQ", initialValue = 100, allocationSize = 1)
public class Account {

    @Column(name = "LOGIN")
    @Size(max = 30, message = "Max size is 30!")
    private final String login;
    @Id
    @GeneratedValue(generator = "ACCOUNT_SEQ")
    private Long id;
    @Column(name = "FIRST_NAME")
    @Size(max = 50, message = "Max size is 50!")
    private String firstName;
    @Column(name = "LAST_NAME")
    @Size(max = 70, message = "Max size is 70!")
    private String lastName;
    @Column(name = "PASSWORD")
    @Size(max = 50, message = "Max size is 50!")
    private String password;
    @Column(name = "EMAIL")
    @Size(max = 60, message = "Max size is 60!")
    private String email;
    @OneToMany(
            mappedBy = "account",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<Address> addresses = new HashSet<>();

    protected Account() {
        this.login = "";
    } // Only for JPA

    public Account(String login) {
        this.login = login;
    }

    public Long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String name) {
        this.lastName = name;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String name) {
        this.password = name;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String name) {
        this.email = name;
    }

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
