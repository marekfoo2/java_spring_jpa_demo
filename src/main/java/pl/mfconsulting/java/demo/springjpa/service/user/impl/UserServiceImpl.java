package pl.mfconsulting.java.demo.springjpa.service.user.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.mfconsulting.java.demo.springjpa.repository.user.UserRepository;
import pl.mfconsulting.java.demo.springjpa.repository.user.entity.User;
import pl.mfconsulting.java.demo.springjpa.service.user.UserService;

@Service
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllPostsByTitleWithComments(String titlePattern, PageRequest pageRequest) {
        return userRepository.findAllByIdWithAddresses(
                userRepository.findAllUserIdsByName(
                        titlePattern,
                        pageRequest));
    }

    @Override
    public void addUser(User user) {
        userRepository.save(user);
    }

}
