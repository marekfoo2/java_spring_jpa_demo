package pl.mfconsulting.java.demo.springjpa.repository.user;

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
import pl.mfconsulting.java.demo.springjpa.repository.user.entity.User;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    UserRepository repository;

    @Test
    public void whenFindByFirstNameThenReturnUsers() {
        User user = new User("login1");
        user.setFirstName("FirstName1");
        User userDB1 = repository.saveAndFlush(user);
        user = new User("login2");
        user.setFirstName("FirstName1");
        User userDB2 = repository.saveAndFlush(user);

        List<User> usersFromDB = repository.findAllByIdWithAddresses(List.of(userDB1.getId(), userDB2.getId()));
        
        //then
        assertEquals(usersFromDB.size(), 2);
    }

    @Test
    public void whenFindByIdThenReturnUser() {
        User user = new User("alada");
        user.setEmail("safsf");
        User userDB = repository.saveAndFlush(user);

        Optional<User> userFromDB = repository.findById(userDB.getId());
        User user1 = userFromDB.get();

        assertTrue(userFromDB.isPresent());
        assertEquals(userDB.getId(), user1.getId());
    }

    @Test
    public void whenSaveWithTooLongEmailThenThrowException() {
        // given
        User user = new User("alada");
        user.setEmail("fsjflsajflsajflsjflsajflsakflsjflsjfsklfjlsdjfasjf2o3u02u30ur0sadlfjasdfassdf");

        // when
        ConstraintViolationException thrown = Assertions.assertThrows(
                jakarta.validation.ConstraintViolationException.class,
                () -> {
                    repository.saveAndFlush(user);
                });

        Assertions.assertTrue(thrown.getMessage().contains("Max size is 60!"));
    }

    @Test
    public void whenSaveAllInBatchInsertThenCountIsCorrectAndFaster() {
        int n = 10;
        List<User> users = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            users.add(new User(MyRandom.randStr(20)));
        }

        List<User> usersDB = repository.saveAllAndFlush(users);

        assertEquals(usersDB.size(), n);
    }

    @Test
    public void whenSaveAllOneByOneThenCountIsCorrect() {
        int n = 10;
        for (int i = 0; i < n; i++) {
            // Very long, not recommended!
            repository.saveAndFlush(new User(MyRandom.randStr(20)));
        }

        assertEquals(repository.findAll().size(), n);
    }

}
