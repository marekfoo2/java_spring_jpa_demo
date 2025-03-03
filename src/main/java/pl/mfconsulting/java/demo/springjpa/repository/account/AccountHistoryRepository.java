package pl.mfconsulting.java.demo.springjpa.repository.account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.AccountHistory;

import java.util.Set;

/**
 * This repo operates only on Address table!
 */
@Repository
public interface AccountHistoryRepository extends JpaRepository<AccountHistory, Long> {

    @Query("Select uh from AccountHistory uh join Account u where u = :account")
    Set<AccountHistory> findAllHistoryForAccount(@Param("account") Account account);
}
