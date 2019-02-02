package org.shop.data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "roles")
public class UserRole {
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private Long Id;
    @Column(name = "user_role")
    private String roleName;
    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();

    private String description;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
