package indi.rennnhong.staterkit.rbac.web.command;

import indi.rennnhong.staterkit.rbac.entity.Role;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * bean of create action
 */
@Data
public class UserCreateCommand {
    @NotEmpty
    private String userName;

    @NotEmpty
    private String account;

    @NotEmpty
    private String password;

    @NotEmpty
    private List<String> roles = new ArrayList<>();

}


