package org.shop.service;

import java.util.List;

import org.shop.data.User;

public interface UserService {
    
    int registerUser(User user);
    
    User getUserById(int id);
    
    void updateUserProfile(User user);
    
    List<User> getUsers();
}
