package org.shop.repository.impl;

import org.hibernate.SessionFactory;
import org.shop.data.UserRole;
import org.shop.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
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
    public List<UserRole> getRoles() {
        return sessionFactory.getCurrentSession().createQuery("from Role").list();
    }
}
