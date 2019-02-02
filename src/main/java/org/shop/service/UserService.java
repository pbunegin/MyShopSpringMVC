package org.shop.service;

import java.util.List;

import org.shop.data.User;

public interface UserService {
    
    Long registerUser(User user);
    
    User getUserById(Long id);
    
    void updateUserProfile(User user);
    
    List<User> getUsers();

    User getUserByLogin(String login);
}
