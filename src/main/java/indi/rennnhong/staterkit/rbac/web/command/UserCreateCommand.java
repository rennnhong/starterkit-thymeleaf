package indi.rennnhong.staterkit.rbac.web.command;

import indi.rennnhong.staterkit.rbac.entity.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * bean of create action
 */
public class UserCreateCommand {
    @NotEmpty
    private String userName;

    @NotNull
    private Set<Role> roles;

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


