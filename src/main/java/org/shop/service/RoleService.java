package org.shop.service;

import org.shop.data.UserRole;

public interface RoleService {
        Long createRole(UserRole role);

        UserRole getRoleById(Long id);

        void updateRole(UserRole role);

        UserRole getRoles();
}
