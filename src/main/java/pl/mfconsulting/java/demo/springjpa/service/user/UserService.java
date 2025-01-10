package pl.mfconsulting.java.demo.springjpa.service.user;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import pl.mfconsulting.java.demo.springjpa.repository.user.entity.User;

public interface UserService {

    void addUser(User user);

    List<User> findAllPostsByTitleWithComments(
            String firstNamePattern,
            PageRequest pageRequest);
}
