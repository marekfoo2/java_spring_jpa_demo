package pl.mfconsulting.java.demo.springjpa.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.mfconsulting.java.demo.springjpa.repository.account.AccountRepository;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;
import pl.mfconsulting.java.demo.springjpa.service.user.AccountService;

import java.util.List;

@Service
class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAllPostsByTitleWithComments(String titlePattern, PageRequest pageRequest) {
        return accountRepository.findAllByIdWithAddresses(
                accountRepository.findAllAccountIdsByName(
                        titlePattern,
                        pageRequest));
    }

    @Override
    public void addUser(Account account) {
        accountRepository.save(account);
    }

}
