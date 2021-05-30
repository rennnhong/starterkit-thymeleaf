package indi.rennnhong.staterkit.rbac.service.mapper;


import indi.rennnhong.staterkit.rbac.entity.Api;
import indi.rennnhong.staterkit.rbac.service.dto.ApiDto;
import indi.rennnhong.staterkit.rbac.service.dto.ApiEditDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Collection;
import java.util.UUID;

@Mapper(
        componentModel = "spring",
        imports = UUID.class
)
public interface ApiMapper {

    @Mapping(target = "id", ignore = true)
    Api createEntity(ApiDto dto);

    void updateEntity(@MappingTarget Api Api, ApiEditDto updateActionRequestDto);

    Api toEntity(ApiDto apiDto);

    ApiDto toDto(Api entity);

    Collection<ApiDto> toDto(Collection<Api> entities);
}
