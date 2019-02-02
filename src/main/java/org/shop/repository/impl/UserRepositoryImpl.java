package org.shop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.shop.data.User;
import org.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@Transactional
public class UserRepositoryImpl implements UserRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserById(Long id) {
        return sessionFactory.getCurrentSession().get(User.class, id);
    }

    @Override
    public Long createUser(User user) {
        return (Long) sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void updateUser(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
    }

    @Override
    public List<User> getUsers() {
        Session session = sessionFactory.getCurrentSession();
        List<User> users = session.createSQLQuery("select * from users").addEntity(User.class).list();
        return users;
    }
}
