package pl.mfconsulting.java.demo.springjpa.repository.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import pl.mfconsulting.java.demo.springjpa.repository.user.entity.User;
import pl.mfconsulting.java.demo.springjpa.repository.user.entity.UserHistory;

@DataJpaTest
public class UserHistoryRepositoryTest {

    @Autowired
    UserRepository userRepo;

    @Autowired
    UserHistoryRepository userHistRepo;

    @Test
    public void whenSaveUserHistoryThenGetTheSame() {
        // given
        User userDB = createUser("aladyn");

        UserHistory userHistory = new UserHistory(userDB.getLogin(), "12.123.23.12", true);
        // userHistory.setUser(userRepo.getReferenceById(userDB.getId()));

        // when
        UserHistory userHistDB = userHistRepo.saveAndFlush(userHistory);

        // then
        assertEquals(userHistDB.getIpAddress(), userHistory.getIpAddress());
    }

    @Test
    public void whenSaveOneUserHistoryThenFindIt() {
        // given
        User userDB = createUser("aladyn");

        UserHistory userHistory = new UserHistory(userDB.getLogin(), "12.123.23.12", true);
        userHistRepo.saveAndFlush(userHistory);

        // when
        List<UserHistory> userHistDBs = userHistRepo.findAll();

        // then
        assertEquals(userHistDBs.stream().findFirst().get().getIpAddress(), userHistory.getIpAddress());
    }

    private User createUser(String login) {
        User user = new User(login);
        user.setEmail("asfdas@safd.pl");
        return userRepo.saveAndFlush(user);
    }
}
