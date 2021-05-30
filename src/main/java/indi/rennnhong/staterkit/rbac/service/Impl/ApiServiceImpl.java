package indi.rennnhong.staterkit.rbac.service.Impl;

import com.google.common.collect.Sets;
import indi.rennnhong.staterkit.rbac.entity.Api;
import indi.rennnhong.staterkit.rbac.entity.Role;
import indi.rennnhong.staterkit.rbac.entity.RolePermission;
import indi.rennnhong.staterkit.rbac.repository.ApiRepository;
import indi.rennnhong.staterkit.rbac.repository.RoleRepository;
import indi.rennnhong.staterkit.rbac.service.ApiService;
import indi.rennnhong.staterkit.rbac.service.dto.ApiDto;
import indi.rennnhong.staterkit.rbac.service.dto.ApiEditDto;
import indi.rennnhong.staterkit.rbac.service.mapper.ApiMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class ApiServiceImpl implements ApiService {

    final ApiRepository apiRepository;

    final ApiMapper apiMapper;

    RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public ApiServiceImpl(ApiRepository apiRepository, ApiMapper apiMapper) {
        this.apiRepository = apiRepository;
        this.apiMapper = apiMapper;
    }

//    @Override
//    public List<ApiDto> getAllApiByRole(UUID roleId, String url, String httpMethod) {
//        Role roleEntity = roleRepository.findById(roleId).get();
//        Set<RolePermission> rolePermissions = roleEntity.getRolePermissions();
//
////        List<Api> apiList = rolePermissions.stream()
////            .map(rolePermission -> rolePermission.getAction().getApi())
////            .filter(item -> url.matches(item.getUrl() + "/.*"))
////            .collect(Collectors.toList());
//        return ImmutableList.copyOf(apiMapper.toDto(getApiList(rolePermissions, url)));
//    }

    @Override
    public Collection<ApiDto> getAll() {
        List<Api> apis = apiRepository.findAll();
        return apiMapper.toDto(apis);
    }

    @Override
    public ApiDto getById(UUID id) {
        Api api = apiRepository.findById(id).get();
        return apiMapper.toDto(api);
    }

    @Override
    public ApiDto getRestFulApi(String url, HttpMethod httpMethod) {
        //todo *處理特殊路徑
        String prefix = "/api";
        String apiUrl = prefix + url;
        Optional<Api> optionalApi = apiRepository.findByUrlAndHttpMethod(apiUrl, httpMethod.name());
        Api api = optionalApi.get();
        return apiMapper.toDto(api);
    }

    @Override
    public ApiDto save(ApiDto apiDto) {
        Api entity = apiMapper.createEntity(apiDto);
        apiRepository.save(entity);
        return apiMapper.toDto(entity);
    }

    @Override
    public ApiDto update(UUID id, ApiEditDto apiEditDto) {
        Api api = apiRepository.findById(id).get();
        apiMapper.updateEntity(api, apiEditDto);
        return apiMapper.toDto(api);
    }

    @Override
    public void delete(UUID id) {
        apiRepository.deleteById(id);
    }

    @Override
    public boolean isExist(UUID id) {
        return apiRepository.existsById(id);
    }

    @Override
    public boolean isAccessibleByRoles(List<UUID> roleIds, ApiDto apiDto) {
        Api api = apiMapper.toEntity(apiDto);
        Set<Api> apiSet = getAllApiByRoles(roleIds);
        return apiSet.contains(api);
    }


    private Set<Api> getAllApiByRoles(List<UUID> roleIds) {

        List<Role> roleEntities = roleRepository.findAllByIdIn(roleIds);

        List<Set<RolePermission>> collect = roleEntities.stream().map(roleEntity -> roleEntity.getRolePermissions())
                .collect(Collectors.toList());

        Set<Api> accessibleApi = Sets.newHashSet();
        for (Set<RolePermission> rolePermissions : collect) {
            Set<Api> apiSet = rolePermissions.stream().map(rolePermission -> rolePermission.getAction().getApi()).collect(Collectors.toSet());
            accessibleApi.addAll(apiSet);
        }

        return accessibleApi;
    }

//    private List<Api> getApiList(Set<RolePermission> rolePermissions, String url) {
//        return rolePermissions.stream()
//                .map(rolePermission -> rolePermission.getAction().getApi())
//                .filter(item -> url.matches(item.getUrl() + "/.*"))
//                .collect(Collectors.toList());
//    }
}
