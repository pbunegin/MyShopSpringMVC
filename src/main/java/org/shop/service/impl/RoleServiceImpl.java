package org.shop.service.impl;

import org.shop.data.UserRole;
import org.shop.repository.RoleRepository;
import org.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleRepository repository;

    @Override
    public Long createRole(UserRole role) {
        return repository.createRole(role);
    }

    @Override
    public UserRole getRoleById(Long id) {
        return repository.getRoleById(id);
    }

    @Override
    public void updateRole(UserRole role) {
        repository.updateRole(role);
    }

    @Override
    public List<UserRole> getRoles() {
        return repository.getRoles();
    }
}
