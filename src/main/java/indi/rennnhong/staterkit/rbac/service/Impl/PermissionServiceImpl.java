package indi.rennnhong.staterkit.rbac.service.Impl;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import indi.rennnhong.staterkit.common.query.PageableResult;
import indi.rennnhong.staterkit.common.query.PageableResultImpl;
import indi.rennnhong.staterkit.common.query.QueryParameter;
import indi.rennnhong.staterkit.rbac.entity.Permission;
import indi.rennnhong.staterkit.rbac.entity.Role;
import indi.rennnhong.staterkit.common.exception.ExceptionFactory;
import indi.rennnhong.staterkit.common.exception.ExceptionType;
import indi.rennnhong.staterkit.rbac.repository.PermissionRepository;
import indi.rennnhong.staterkit.rbac.repository.RoleRepository;
import indi.rennnhong.staterkit.rbac.service.PermissionService;
import indi.rennnhong.staterkit.rbac.service.dto.PermissionDto;
import indi.rennnhong.staterkit.rbac.service.dto.PermissionEditDto;
import indi.rennnhong.staterkit.rbac.service.dto.RoleDto;
import indi.rennnhong.staterkit.rbac.service.mapper.PermissionMapper;
import indi.rennnhong.staterkit.rbac.service.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static indi.rennnhong.staterkit.common.exception.GroupType.PERMISSION;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    final PermissionRepository permissionRepository;

    final PermissionMapper permissionMapper;

    RoleRepository roleRepository;

    RoleMapper roleMapper;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setRoleMapper(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    @Autowired
    public PermissionServiceImpl(PermissionRepository permissionRepository, PermissionMapper permissionMapper) {
        this.permissionRepository = permissionRepository;
        this.permissionMapper = permissionMapper;
    }


    @Override
    public Collection<PermissionDto> getAll() {
        List<Permission> permissions = permissionRepository.findAll();
        return permissionMapper.toDto(permissions);
    }

    @Override
    public PermissionDto getById(UUID id) {
        Permission permission = permissionRepository.findById(id).get();
        return permissionMapper.toDto(permission);
    }

    @Override
    public PermissionDto save(PermissionDto permissionDto) {
        Permission entity = permissionMapper.createEntity(permissionDto);

        Permission parent = permissionRepository.findById(permissionDto.getParentId()).get();
        entity.setParent(parent);

        permissionRepository.save(entity);
        return permissionMapper.toDto(entity);
    }

    @Override
    public PermissionDto update(UUID id, PermissionEditDto permissionEditDto) {
        Permission permission = permissionRepository.findById(id).get();
        permissionMapper.updateEntity(permission, permissionEditDto);
        return permissionMapper.toDto(permission);
    }

    @Override
    public void delete(UUID id) {
        Optional<Permission> optionalPermission = permissionRepository.findById(id);
        Permission permission = optionalPermission.orElseThrow(() ->
                ExceptionFactory.newException(PERMISSION, ExceptionType.ENTITY_NOT_FOUND, id.toString())
        );
        permissionRepository.delete(permission);
    }

    @Override
    public boolean isExist(UUID id) {
        return permissionRepository.existsById(id);
    }

    @Override
    public boolean isLastLayer(UUID id) {
        //若不為任何Page的pid，即為葉子節點
        return !permissionRepository.existsByParentId(id);
    }

    @Override
    public PageableResult<PermissionDto> pageAll(Integer pageNumber, Integer rowsPerPage) {
        QueryParameter qp = new QueryParameter()
                .addPageNumber(pageNumber)
                .addRowsPerPage(rowsPerPage)
                .build();

        Page<Permission> resultPage = permissionRepository.findAll(
                PageRequest.of(qp.getPageOffset(), qp.getPageLimit()));

        List<PermissionDto> dtos = ImmutableList
                .copyOf(permissionMapper.toDto(resultPage.getContent()));

        return new PageableResultImpl(
                qp.getPageLimit(),
                qp.getPageNumber(),
                resultPage.getTotalPages(),
                resultPage.getTotalElements(),
                dtos);
    }

    @Override
    public Set<PermissionDto> getAllPermissions(UUID roleId) {
        Role role = roleRepository.findById(roleId).get();
        Set<Permission> collect = role.getRolePermissions()
                .stream()
                .map(rolePermission -> rolePermission.getPermission())
                .collect(Collectors.toSet());
        return ImmutableSet.copyOf(permissionMapper.toDto(collect));
    }

    @Override
    public Set<PermissionDto> getPermissionsByRoles(Collection<RoleDto> roles) {
//        Collection<Role> roleEntities = roleMapper.toEntity(roles);
//        Set<RolePermission> rolePermissions = roleEntities.stream()
//                .map(role -> role.getRolePermissions())
//                .reduce((resultSet, set) -> {
//                    resultSet.addAll(set);
//                    return resultSet;
//                }).get();
//
//        Set<Permission> permissions = rolePermissions.stream()
//                .map(rolePermission -> rolePermission.getPermission())
//                .collect(Collectors.toSet());
//
//        return ImmutableSet.copyOf(permissionMapper.toDto(permissions));
        return null;
    }
}
