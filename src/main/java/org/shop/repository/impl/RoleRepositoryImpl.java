package org.shop.repository.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.shop.data.UserRole;
import org.shop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Long createRole(UserRole role) {
        return (Long) sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public UserRole getRoleById(Long id) {
        return sessionFactory.getCurrentSession().get(UserRole.class, id);
    }

    @Override
    public void updateRole(UserRole role) {
        sessionFactory.getCurrentSession().update(role);
    }

    @Override
    public UserRole getRoles() {
        Session session = sessionFactory.getCurrentSession();
        UserRole roles = (UserRole) session.createSQLQuery("select * from roles").addEntity(UserRole.class).list();
        return roles;
    }
}
