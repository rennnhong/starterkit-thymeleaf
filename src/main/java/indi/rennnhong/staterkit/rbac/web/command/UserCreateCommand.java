package indi.rennnhong.staterkit.rbac.web.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
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


