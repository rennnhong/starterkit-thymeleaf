package indi.rennnhong.staterkit.rbac.service;

import indi.rennnhong.staterkit.rbac.service.dto.PermissionDto;
import indi.rennnhong.staterkit.rbac.service.dto.PermissionEditDto;
import indi.rennnhong.staterkit.rbac.service.dto.RoleDto;
import indi.rennnhong.staterkit.common.query.PageableResult;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

public interface PermissionService {

    Collection<PermissionDto> getAll();

    PermissionDto getById(UUID id);

    PermissionDto save(PermissionDto permissionDto);

    PermissionDto update(UUID id, PermissionEditDto permissionEditDto);

    void delete(UUID id);

    boolean isExist(UUID id);

    boolean isLastLayer(UUID id);

    PageableResult<PermissionDto> pageAll(Integer pageNumber, Integer rowsPerPage);

    Set<PermissionDto> getAllPermissions(UUID roleId);

    Set<PermissionDto> getPermissionsByRoles(Collection<RoleDto> roles);

}
