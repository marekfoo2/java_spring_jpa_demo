package pl.mfconsulting.java.demo.springjpa.service.user;

import org.springframework.data.domain.PageRequest;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;

import java.util.List;

public interface AccountService {

    void addUser(Account account);

    List<Account> findAllPostsByTitleWithComments(
            String firstNamePattern,
            PageRequest pageRequest);
}
