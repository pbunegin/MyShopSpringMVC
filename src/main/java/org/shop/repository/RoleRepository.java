package org.shop.repository;

import org.shop.data.UserRole;

public interface RoleRepository {
    Long createRole(UserRole role);

    UserRole getRoleById(Long id);

    void updateRole(UserRole role);

    UserRole getRoles();
}
