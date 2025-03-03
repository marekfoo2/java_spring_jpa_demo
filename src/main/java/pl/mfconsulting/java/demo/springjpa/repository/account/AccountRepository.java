package pl.mfconsulting.java.demo.springjpa.repository.account;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;

import java.util.List;
import java.util.Set;

/**
 * This repository only operates on Account table only.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("select u from Account u")
    Set<Account> find();

    @Query("Select u from Account u where u.login = :login")
    Account findByLogin(@Param("login") String login);

    @Query(value = """
            select u.id
            from Account u
            where u.firstName like :firstNamePattern
            """
            , countQuery = """
            select count(u)
            from Account u
            where u.firstName like :firstNamePattern
            """)
    List<Long> findAllAccountIdsByName(
            @Param("firstNamePattern") String firstNamePattern,
            Pageable pageable);

    @Query("""
            select u
            from Account u
            left join fetch u.addresses
            where u.id in :accountIds
            """)
    List<Account> findAllByIdWithAddresses(
            @Param("accountIds") List<Long> accountIds);
}
