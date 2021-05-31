package indi.rennnhong.staterkit.rbac.service.mapper;

import indi.rennnhong.staterkit.rbac.entity.Action;
import indi.rennnhong.staterkit.rbac.service.dto.ActionDto;
import indi.rennnhong.staterkit.rbac.service.dto.ActionEditDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        imports = UUID.class
)
public interface ActionMapper {

    @Mapping(target = "id", ignore = true)
    Action createEntity(ActionDto dto);

    void updateEntity(@MappingTarget Action entity, ActionEditDto actionEditDto);

    ActionDto toDto(Action entity);

    Collection<ActionDto> toDto(Collection<Action> entities);
}
