package indi.rennnhong.staterkit.rbac.service;

import indi.rennnhong.staterkit.rbac.entity.Permission;
import indi.rennnhong.staterkit.rbac.service.dto.ActionDto;
import indi.rennnhong.staterkit.rbac.service.dto.ActionEditDto;
import indi.rennnhong.staterkit.common.query.PageableResult;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface ActionService {

    Collection<ActionDto> getAll(UUID permissionId);

    ActionDto getById(UUID id);

    ActionDto save(UUID permissionId, ActionDto actionDto);

    ActionDto update(UUID id, UUID permissionId, ActionEditDto actionEditDto);

    void delete(UUID id);

    boolean isExist(UUID id);

    PageableResult<ActionDto> pageAllByPermission(Permission permission, Integer pageNumber, Integer rowsPerPage);

    Set<ActionDto> getActionsByPermissionId(String permissionId);


}
