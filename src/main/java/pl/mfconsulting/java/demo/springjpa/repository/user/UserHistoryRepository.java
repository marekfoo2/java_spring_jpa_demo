package pl.mfconsulting.java.demo.springjpa.repository.user;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.mfconsulting.java.demo.springjpa.repository.user.entity.User;
import pl.mfconsulting.java.demo.springjpa.repository.user.entity.UserHistory;

/**
 * This repo operates only on Address table!
 */
@Repository
public interface UserHistoryRepository extends JpaRepository<UserHistory, Long> {

    @Query("Select uh from UserHistory uh join User u where u = :user")
    Set<UserHistory> findAllHistoryForUser(@Param("user") User user);
}
