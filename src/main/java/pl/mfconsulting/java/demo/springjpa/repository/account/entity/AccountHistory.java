package pl.mfconsulting.java.demo.springjpa.repository.account.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity
@Table(name = "ACCOUNT_HISTORY")
@SequenceGenerator(name = "ACCOUNT_HISTORY_SEQ", sequenceName = "ACCOUNT_HISTORY_ID_SEQ", initialValue = 100, allocationSize = 1)
public class AccountHistory {

    @Id
    @GeneratedValue(generator = "ACCOUNT_HISTORY_SEQ")
    private Long id;

    @Column(name = "LOGIN", nullable = false)
    @Size(max = 30, message = "Max size is 30!")
    private String login;

    @Column(name = "LAST_LOGIN", nullable = false)
    private LocalDateTime lastLogin;

    @Column(name = "IP_ADDRESS", nullable = false)
    @Size(max = 50, message = "Max size is 50!")
    private String ipAddress;

    @Column(name = "IS_SUCCESS", nullable = false, length = 1)
    private String isSuccess;

    protected AccountHistory() {
        this.login = "";
    }

    public AccountHistory(String login, String ipAddress, Boolean isSuccess) {
        this.login = login;
        this.ipAddress = ipAddress;
        this.lastLogin = LocalDateTime.now();
        setIsSuccess(isSuccess);
    }

    public Long getId() {
        return this.id;
    }

    public String getLogin() {
        return this.login;
    }

    protected void setLogin(String name) {
        this.login = name;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    protected void setIpAddress(String name) {
        this.ipAddress = name;
    }

    public Boolean isSuccess() {
        return Boolean.valueOf(isSuccess);
    }

    protected void setIsSuccess(Boolean string) {
        this.isSuccess = (string) ? "1" : "0";
    }
}
