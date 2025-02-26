package pl.mfconsulting.java.demo.springjpa.repository.account;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Account;
import pl.mfconsulting.java.demo.springjpa.repository.account.entity.Address;

/**
 * This repo operates only on Address table!
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("Select add from Account acc join acc.addresses add where acc = :account")
    Set<Address> findAllAddressesForAccount(@Param("account") Account account);

    @Query("Select add from Address add join fetch add.account acc where acc = :account")
    Set<Address> findAllAddressesWithAccount(@Param("account") Account account);

    @Modifying
    @Query("update Address a set a.street = :street where a.account =:account")
    void updateStreetForAccount(@Param("street") String street, @Param("account") Account account);
}
