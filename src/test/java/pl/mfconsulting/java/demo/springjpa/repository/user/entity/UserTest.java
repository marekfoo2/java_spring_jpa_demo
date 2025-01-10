package pl.mfconsulting.java.demo.springjpa.repository.user.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

// import nl.jqno.equalsverifier.EqualsVerifier;

public class UserTest {
  
  @Test
  void verifyEquals() {
    User user1 = new User("12");
    User user2 = new User("12");

    Set<User> users = new HashSet<>();
    users.add(user2);
    users.add(user1);

    assertEquals(users.size(), 1);
    // EqualsVerifier.forClass(User.class).withOnlyTheseFields("login").verify();
  }
}
