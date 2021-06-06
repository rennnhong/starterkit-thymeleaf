package indi.rennnhong.staterkit.rbac.web.command;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
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

    @NotEmpty
    private List<String> roles = new ArrayList<>();

}


