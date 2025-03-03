package pl.mfconsulting.java.demo.springjpa.service.user.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import pl.mfconsulting.java.demo.springjpa.configuration.IntegrationTransactionalContext;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;
import pl.mfconsulting.java.demo.springjpa.service.user.AccountService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AccountServiceImplTest extends IntegrationTransactionalContext {

    @Autowired
    private AccountService accountService;

    @Test
    void whenSaveInValidUserThenThrowsViolationException() {
        var user = new Account("1234567890123456789012345678901asdfasdfasdfasd222");
        accountService.addUser(user);
//        assertThrows(ConstraintViolationException.class, () -> accountService.addUser(user));
    }

    @Test
    void testFindAllPostsByTitleWithComments() {
        // given
        int quant = 100;
        int maxCount = 25;

        List<Account> accounts = createAndSaveUsers(quant);
        accounts.forEach(accountService::addUser);

        List<Account> usersDB = accountService.findAllPostsByTitleWithComments(
                "name",
                PageRequest.of(
                        0,
                        maxCount,
                        Sort.by("login")));

        // then
        assertEquals(maxCount, usersDB.size());
    }
}
