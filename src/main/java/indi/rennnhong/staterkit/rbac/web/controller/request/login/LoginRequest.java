package indi.rennnhong.staterkit.rbac.web.controller.request.login;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {

    @NotEmpty
    private String username;

    @NotEmpty
    private String password;
}
