package indi.rennnhong.staterkit.rbac.web.command;

import indi.rennnhong.staterkit.rbac.entity.Role;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

/**
 * bean of create action
 */
public class UserUpdateCommand {

    private UUID id;

    @NotEmpty
    private String userName;

    @NotNull
    private Set<Role> roles;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}


