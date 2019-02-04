package org.shop.service;

import org.shop.data.UserRole;

import java.util.List;

public interface RoleService {
        Long createRole(UserRole role);

        UserRole getRoleById(Long id);

        void updateRole(UserRole role);

        List<UserRole> getRoles();
}
