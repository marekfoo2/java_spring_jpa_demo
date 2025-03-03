package pl.mfconsulting.java.demo.springjpa.repository.account;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pl.mfconsulting.java.demo.springjpa.configuration.IntegrationTransactionalContext;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.AccountHistory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountHistoryRepositoryTest extends IntegrationTransactionalContext {

    @Autowired
    AccountRepository userRepo;

    @Autowired
    AccountHistoryRepository userHistRepo;

    @Test
    public void whenSaveUserHistoryThenGetTheSame() {
        // given
        Account accountDB = createUser("aladyn");

        AccountHistory accountHistory = new AccountHistory(accountDB.getLogin(), "12.123.23.12", true);
        // userHistory.setUser(userRepo.getReferenceById(userDB.getId()));

        // when
        AccountHistory userHistDB = userHistRepo.saveAndFlush(accountHistory);

        // then
        assertEquals(userHistDB.getIpAddress(), accountHistory.getIpAddress());
    }

    @Test
    public void whenSaveOneUserHistoryThenFindIt() {
        // given
        Account accountDB = createUser("aladyn");

        AccountHistory accountHistory = new AccountHistory(accountDB.getLogin(), "12.123.23.12", true);
        userHistRepo.saveAndFlush(accountHistory);

        // when
        List<AccountHistory> userHistDBs = userHistRepo.findAll();

        // then
        assertEquals(userHistDBs.stream().findFirst().get().getIpAddress(), accountHistory.getIpAddress());
    }

    private Account createUser(String login) {
        Account account = new Account(login);
        account.setEmail("asfdas@safd.pl");
        return userRepo.saveAndFlush(account);
    }
}
