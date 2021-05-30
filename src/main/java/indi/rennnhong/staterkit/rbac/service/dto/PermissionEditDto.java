package indi.rennnhong.staterkit.rbac.service.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;


@Data
public class PermissionEditDto {

    @NotEmpty
    String name;

    String sorted;

    String route;

    String opened;

    String icon;
}
