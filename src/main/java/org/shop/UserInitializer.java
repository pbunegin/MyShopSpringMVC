package org.shop;

import org.shop.service.UserService;
import org.shop.data.User;
import org.springframework.beans.factory.annotation.Autowired;

public class UserInitializer {

    @Autowired
    private UserService userService;

    public void initUsers() {
        User user = null;
        
        user = new User();
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setPassword("111");
        userService.registerUser(user);
        
        user = new User();
        user.setFirstName("Petr");
        user.setLastName("Petrov");
        user.setPassword("222");
        userService.registerUser(user);
    }
}
