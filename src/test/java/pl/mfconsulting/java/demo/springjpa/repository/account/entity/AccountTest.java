package pl.mfconsulting.java.demo.springjpa.repository.account.entity;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

// import nl.jqno.equalsverifier.EqualsVerifier;

public class AccountTest {

    @Test
    void verifyEquals() {
        Account account1 = new Account("12");
        Account account2 = new Account("12");

        Set<Account> accounts = new HashSet<>();
        accounts.add(account2);
        accounts.add(account1);

        assertEquals(accounts.size(), 1);
        // EqualsVerifier.forClass(User.class).withOnlyTheseFields("login").verify();
    }
}
