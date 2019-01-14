package org.shop;

import org.shop.api.UserService;
import org.shop.data.User;

public class UserInitializer {

    private UserService userService;

    public UserInitializer(UserService userService) {
        super();
        this.userService = userService;
    }
    
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
