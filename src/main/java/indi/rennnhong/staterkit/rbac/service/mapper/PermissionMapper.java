package indi.rennnhong.staterkit.rbac.service.mapper;

import indi.rennnhong.staterkit.rbac.entity.Permission;
import indi.rennnhong.staterkit.rbac.service.dto.PermissionDto;
import indi.rennnhong.staterkit.rbac.service.dto.PermissionEditDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        imports = UUID.class
)
public interface PermissionMapper {

    @Mapping(target = "id", ignore = true)
    Permission createEntity(PermissionDto dto);

    void updateEntity(@MappingTarget Permission entity, PermissionEditDto dto);

    PermissionDto toDto(Permission entity);

    Collection<PermissionDto> toDto(Collection<Permission> entities);
}
