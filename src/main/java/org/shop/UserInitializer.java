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
        user.setFirstName("Иван");
        user.setLastName("Иванов");
        user.setLogin("ivan");
        user.setPassword("111");
//        user.setRole("admin");
        userService.registerUser(user);
        
        user = new User();
        user.setFirstName("Петр");
        user.setLastName("Петров");
        user.setLogin("petr");
        user.setPassword("222");
        userService.registerUser(user);
    }
}
