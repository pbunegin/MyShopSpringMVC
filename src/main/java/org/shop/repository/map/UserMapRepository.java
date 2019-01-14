package org.shop.repository.map;

import org.shop.data.User;
import org.shop.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UserMapRepository extends AbstractMapRepository<User> implements UserRepository {

    @Override
    public User getUserById(Long id) {
        return get(id);
    }

    @Override
    public Long createUser(User user) {
        return create(user);
    }

    @Override
    public void updateUser(User user) {
        update(user);
    }

    @Override
    public List<User> getUsers() {
        return new ArrayList<User>(register.values());
    }
}