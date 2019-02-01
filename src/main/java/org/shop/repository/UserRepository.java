package org.shop.repository;

import java.util.List;

import org.shop.data.User;

public interface UserRepository {
    
    User getUserById(int id);
    
    long createUser(User user);
    
    void updateUser(User user);
    
    List<User> getUsers();
}
