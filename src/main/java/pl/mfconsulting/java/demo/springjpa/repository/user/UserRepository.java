package pl.mfconsulting.java.demo.springjpa.repository.user;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.mfconsulting.java.demo.springjpa.repository.user.entity.User;

/**
 * This repository only operates on User table only.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u")
    Set<User> find();

    @Query("Select u from User u where u.login = :login")
    User findByLogin(@Param("login") String login);

    @Query(value = """
            select u.id
            from User u
            where u.firstName like :firstNamePattern
            """
            , countQuery = """
            select count(u)
            from User u
            where u.firstName like :firstNamePattern
            """)
    List<Long> findAllUserIdsByName(
            @Param("firstNamePattern") String firstNamePattern,
            Pageable pageable);

    @Query("""
            select u
            from User u
            left join fetch u.addresses
            where u.id in :userIds
            """)
    List<User> findAllByIdWithAddresses(
            @Param("userIds") List<Long> userIds);
}
