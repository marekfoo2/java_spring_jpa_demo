package pl.mfconsulting.java.demo.springjpa.repository.user;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.mfconsulting.java.demo.springjpa.repository.user.entity.Address;
import pl.mfconsulting.java.demo.springjpa.repository.user.entity.User;

/**
 * This repo operates only on Address table!
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("Select a from User u join Address a where u = :user")
    Set<Address> findAllAddressesForUser(@Param("user") User user);

    @Query("Select a from Address a join fetch User u where u = :user")
    Set<Address> findAllAddressesWithUser(@Param("user") User user);

    @Modifying
    @Query("update Address a set a.street = :street where a.user =:user")
    void updateStreetForUser(@Param("street") String street, @Param("user") User user);
}
