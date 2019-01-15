package org.shop.service.impl;

import java.util.List;

import org.shop.service.UserService;
import org.shop.data.User;
import org.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public int registerUser(User user) {
        return repository.createUser(user);
    }

    @Override
    public User getUserById(int userId) {
        return repository.getUserById(userId);
    }

    @Override
    public void updateUserProfile(User user) {
        repository.updateUser(user);
    }

    @Override
    public List<User> getUsers() {
        return repository.getUsers();
    }

    @Override
    public User getUserByLogin(String login) {
        return repository.getUsers().stream().filter(user -> user.getLogin().equals(login)).findFirst().orElse(null);
    }
}
