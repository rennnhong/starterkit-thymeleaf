package indi.rennnhong.staterkit.rbac.web.command;

import indi.rennnhong.staterkit.rbac.entity.Role;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;

/**
 * bean of create action
 */
@Data
public class UserUpdateCommand {

    private UUID id;

    @NotEmpty
    private String userName;

    @NotNull
    private List<String> roles;

}


