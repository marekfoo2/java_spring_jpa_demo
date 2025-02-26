package pl.mfconsulting.java.demo.springjpa.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import pl.mfconsulting.java.demo.springjpa.repository.account.AccountRepository;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;


@SpringBootTest
public class IntegrationTransactionalContext extends AbstractTransactionalJUnit4SpringContextTests{
    
    @Autowired
    private AccountRepository accountRepository;

    public List<Account> createAndSaveUsers(int quant) {
        List<Account> accounts = createUsers(quant);

        accountRepository.saveAllAndFlush(accounts);
        return accounts;
    }

    public List<Account> createUsers(int quant) {
        List<Account> accounts = new ArrayList<>(quant);
        for (int i = 0; i < quant; i++) {
            Account account1 = new Account("login" + i);
            account1.setFirstName("name");
            account1.setLastName("lastName" + i);
            accounts.add(account1);
        }

        return accounts;
    }
}
