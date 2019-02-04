package org.shop.repository;

import org.shop.data.UserRole;

import java.util.List;

public interface RoleRepository {
    Long createRole(UserRole role);

    UserRole getRoleById(Long id);

    void updateRole(UserRole role);

    List<UserRole> getRoles();
}
