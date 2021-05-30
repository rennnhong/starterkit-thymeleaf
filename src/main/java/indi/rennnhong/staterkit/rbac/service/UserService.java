package indi.rennnhong.staterkit.rbac.service;

import indi.rennnhong.staterkit.rbac.service.dto.UserDto;
import indi.rennnhong.staterkit.rbac.service.dto.UserEditDto;
import indi.rennnhong.staterkit.common.query.PageableResult;

import java.util.Collection;
import java.util.UUID;

public interface UserService {

    PageableResult<UserDto> pageAll(Integer pageNumber, Integer rowsPerPage);

    UserDto getUserByAccount(String account);

    boolean isExistByAccount(String userAccount);

    Collection<UserDto> getUsersOfRole(UUID roleId);

    Collection<UserDto> getAll();

    UserDto getById(UUID id);

    UserDto save(UserDto userDto);

    UserDto update(UUID id, UserEditDto userEditDto);

    void delete(UUID id);

    boolean isExist(UUID id);

}
