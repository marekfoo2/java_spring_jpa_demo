package pl.mfconsulting.java.demo.springjpa.repository.account.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ADDRESS")
@SequenceGenerator(name = "ADDRESS_SEQ", sequenceName = "ADDRESS_ID_SEQ", initialValue = 100, allocationSize = 1)
public class Address {
    protected Address() {
        this.addressType = "default";
    } // Only for JPA

    public Address(String addressType, String street, String zipCode, String city) {
        this.addressType = addressType;
        this.street = street;
        this.zipCode = zipCode;
        this.city = city;
    }

    @Id
    @GeneratedValue(generator = "ADDRESS_SEQ")
    private Long id;

    public Long getId() {
        return this.id;
    }

    @Column(name = "STREET")
    @Size(max = 70, message = "Max size is 70!")
    private String street;

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String name) {
        this.street = name;
    }

    @Column(name = "ZIP_CODE")
    @Size(max = 10, message = "Max size is 10!")
    private String zipCode;

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String name) {
        this.zipCode = name;
    }

    @Column(name = "CITY")
    @Size(max = 70, message = "Max size is 70!")
    private String city;

    public String getCity() {
        return this.city;
    }

    public void setCity(String name) {
        this.city = name;
    }

    @Column(name = "ADDRESS_TYPE")
    @Size(max = 20, message = "Max size is 20!")
    private String addressType;

    public String getAddressType() {
        return this.addressType;
    }

    public void setAddressType(String name) {
        this.addressType = name;
    }

    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID", nullable = false)
    private Account account;

    public Account getUser() {
        return this.account;
    }

    public void setUser(Account account) {
        this.account = account;
    }
}
