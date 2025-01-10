package pl.mfconsulting.java.demo.springjpa.configuration;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import pl.mfconsulting.java.demo.springjpa.repository.user.UserRepository;
import pl.mfconsulting.java.demo.springjpa.repository.user.entity.User;


@SpringBootTest
public class IntegrationTransactionalContext extends AbstractTransactionalJUnit4SpringContextTests{
    
    @Autowired
    private UserRepository userRepository;

    public List<User> createAndSaveUsers(int quant) {
        List<User> users = createUsers(quant);

        userRepository.saveAllAndFlush(users);
        return users;
    }

    public List<User> createUsers(int quant) {
        List<User> users = new ArrayList<>(quant);
        for (int i = 0; i < quant; i++) {
            User user1 = new User("login" + i);
            user1.setFirstName("name");
            user1.setLastName("lastName" + i);
            users.add(user1);
        }

        return users;
    }
}
