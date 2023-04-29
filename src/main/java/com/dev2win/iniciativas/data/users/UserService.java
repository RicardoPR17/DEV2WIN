package com.dev2win.iniciativas.data.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    public User getUserByName(String userName) {
        return userRepository.findByName(userName).get();
    }

    public User getUserByMail(String userMail) {
        if (userRepository.findByMail(userMail).isPresent()) {
            return userRepository.findByMail(userMail).get();
        }
        return null;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUsuario(User user) {
        if (userRepository.existsById(user.getUserId())) {
            return userRepository.save(user);
        }
        return null;
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<User> getUserByProfile(String profileToSearch) {
        return userRepository.findByProfile(profileToSearch);
    }

    public List<User> getUserByRole(String roleToSearch) {
        return userRepository.findByRole(roleToSearch);
    }
    public void deleteAll() { userRepository.deleteAll(); }
}
