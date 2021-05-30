package indi.rennnhong.staterkit.rbac.service.mapper;

import com.google.common.collect.ImmutableList;
import indi.rennnhong.staterkit.rbac.entity.Role;
import indi.rennnhong.staterkit.rbac.entity.User;
import indi.rennnhong.staterkit.rbac.service.dto.UserDto;
import indi.rennnhong.staterkit.rbac.service.dto.UserEditDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(
        componentModel = "spring",
        imports = UUID.class
)
public interface UserMapper {

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    User createEntity(UserDto dto);

    @Mapping(target = "roles", ignore = true)
    void updateEntity(@MappingTarget User entity, UserEditDto dto);

    UserDto toDto(User entity);

    Collection<UserDto> toDto(Collection<User> entities);

    default List<String> rolesToRoleIds(Set<Role> roles) {
        if (ObjectUtils.isEmpty(roles)) return ImmutableList.of();
        return roles.stream().map(role -> role.getId().toString()).collect(Collectors.toList());

    }

}
