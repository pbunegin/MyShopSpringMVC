package org.shop.repository.list;

import org.shop.data.User;
import org.shop.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

//@Repository
public class UserListRepository implements UserRepository {
    private List<User> users = new ArrayList<>();
    private int lastId  = 0;

    @Override
    public User getUserById(int id) {
        return users.get(id);
    }

    @Override
    public long createUser(User user) {
        users.add(user);
        user.setId(++lastId);
        return lastId;
    }

    @Override
    public void updateUser(User user) {
        users.set(users.indexOf(user), user);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }
}
