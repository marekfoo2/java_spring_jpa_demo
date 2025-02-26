package pl.mfconsulting.java.demo.springjpa.service.user.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import jakarta.validation.ConstraintViolationException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import pl.mfconsulting.java.demo.springjpa.configuration.IntegrationTransactionalContext;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;
import pl.mfconsulting.java.demo.springjpa.service.user.UserService;


public class AccountServiceImplTest extends IntegrationTransactionalContext{

    @Autowired
    private UserService userService;

    @Test
    void whenSaveInValidUserThenThrowsViolationException(){
        var user = new User("1234567890123456789012345678901222");

        assertThrows(ConstraintViolationException.class, () ->userService.addUser(user));
    }

    @Test
    void testFindAllPostsByTitleWithComments() {
        // given
        int quant = 100;
        int maxCount = 25;

        List<Account> accounts = createAndSaveUsers(quant);
        accounts.forEach(userService::addUser);

        List<Account> usersDB = userService.findAllPostsByTitleWithComments(
                "name",
                PageRequest.of(
                        0,
                        maxCount,
                        Sort.by("login")));

        // then
        assertEquals(maxCount, usersDB.size());
    }    
}
