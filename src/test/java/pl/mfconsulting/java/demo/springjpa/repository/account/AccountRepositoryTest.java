package pl.mfconsulting.java.demo.springjpa.repository.account;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import jakarta.validation.ConstraintViolationException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pl.mfconsulting.java.demo.springjpa.repository.common.random.MyRandom;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;

@DataJpaTest
public class AccountRepositoryTest {

    @Autowired
    AccountRepository repository;

    @Test
    public void whenFindByFirstNameThenReturnUsers() {
        Account account = new Account("login1");
        account.setFirstName("FirstName1");
        Account accountDB1 = repository.saveAndFlush(account);
        account = new Account("login2");
        account.setFirstName("FirstName1");
        Account accountDB2 = repository.saveAndFlush(account);

        List<Account> usersFromDB = repository.findAllByIdWithAddresses(List.of(accountDB1.getId(), accountDB2.getId()));
        
        //then
        assertEquals(usersFromDB.size(), 2);
    }

    @Test
    public void whenFindByIdThenReturnUser() {
        Account account = new Account("alada");
        account.setEmail("safsf");
        Account accountDB = repository.saveAndFlush(account);

        Optional<Account> userFromDB = repository.findById(accountDB.getId());
        Account account1 = userFromDB.get();

        assertTrue(userFromDB.isPresent());
        assertEquals(accountDB.getId(), account1.getId());
    }

    @Test
    public void whenSaveWithTooLongEmailThenThrowException() {
        // given
        Account account = new Account("alada");
        account.setEmail("fsjflsajflsajflsjflsajflsakflsjflsjfsklfjlsdjfasjf2o3u02u30ur0sadlfjasdfassdf");

        // when
        ConstraintViolationException thrown = Assertions.assertThrows(
                jakarta.validation.ConstraintViolationException.class,
                () -> {
                    repository.saveAndFlush(account);
                });

        Assertions.assertTrue(thrown.getMessage().contains("Max size is 60!"));
    }

    @Test
    public void whenSaveAllInBatchInsertThenCountIsCorrectAndFaster() {
        int n = 10;
        List<Account> accounts = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            accounts.add(new Account(MyRandom.randStr(20)));
        }

        List<Account> usersDB = repository.saveAllAndFlush(accounts);

        assertEquals(usersDB.size(), n);
    }

    @Test
    public void whenSaveAllOneByOneThenCountIsCorrect() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            // Very long, not recommended!
            repository.saveAndFlush(new Account(MyRandom.randStr(20)));
        }

        assertEquals(repository.findAll().size(), n);
    }

}
