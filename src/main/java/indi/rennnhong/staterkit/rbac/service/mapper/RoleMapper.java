package indi.rennnhong.staterkit.rbac.service.mapper;


import indi.rennnhong.staterkit.rbac.entity.Role;
import indi.rennnhong.staterkit.rbac.service.dto.RoleDto;
import indi.rennnhong.staterkit.rbac.service.dto.RoleEditDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        imports = UUID.class
)
public interface RoleMapper {

    @Mapping(target = "id", ignore = true)
    Role createEntity(RoleDto dto);

    void updateEntity(@MappingTarget Role role, RoleEditDto dto);

    RoleDto toDto(Role entity);

    Collection<RoleDto> toDto(Collection<Role> entities);

}
