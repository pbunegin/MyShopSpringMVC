package org.shop.repository.list;

import org.shop.data.User;
import org.shop.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserListRepository implements UserRepository {
    private List<User> users = new ArrayList<>();

    @Override
    public User getUserById(Long id) {
        return null;
    }

    @Override
    public Long createUser(User user) {
//        users.add(user);
//        return users.lastIndexOf(user);
        return 0L;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public List<User> getUsers() {
        return null;
    }
}
