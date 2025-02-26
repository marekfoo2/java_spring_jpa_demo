package pl.mfconsulting.java.demo.springjpa.service.user;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;

public interface UserService {

    void addUser(Account account);

    List<Account> findAllPostsByTitleWithComments(
            String firstNamePattern,
            PageRequest pageRequest);
}
