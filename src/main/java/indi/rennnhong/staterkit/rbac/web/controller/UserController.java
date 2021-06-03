package indi.rennnhong.staterkit.rbac.web.controller;

import com.google.common.collect.Lists;
import indi.rennnhong.staterkit.common.query.PageableResult;
import indi.rennnhong.staterkit.common.response.ResponseBody;
import indi.rennnhong.staterkit.common.utils.BindingResultHelper;
import indi.rennnhong.staterkit.common.web.support.DtSpecification;
import indi.rennnhong.staterkit.module.student.model.entity.Student;
import indi.rennnhong.staterkit.rbac.entity.Role;
import indi.rennnhong.staterkit.rbac.entity.User;
import indi.rennnhong.staterkit.rbac.service.UserService;
import indi.rennnhong.staterkit.rbac.service.dto.UserDto;
import indi.rennnhong.staterkit.rbac.service.dto.UserEditDto;
import indi.rennnhong.staterkit.rbac.web.command.UserCreateCommand;
import indi.rennnhong.staterkit.rbac.web.command.UserUpdateCommand;
import indi.rennnhong.staterkit.util.ThymeleafPathUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static indi.rennnhong.staterkit.common.response.ErrorMessages.INVALID_FIELDS_REQUEST;
import static org.springframework.util.StringUtils.hasText;

@RestController
@RequestMapping("/api/users")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getUsers(@RequestParam(defaultValue = "1") int pageNumber,
                                   @RequestParam(defaultValue = "10") int rowsPerPage) {
        PageableResult<UserDto> result = userService.pageAll(pageNumber, rowsPerPage);
        ResponseBody<Collection<UserDto>> responseBody = ResponseBody.newPageableBody(result);
        return new ResponseEntity(responseBody, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable UUID id) {
        return new ResponseEntity(userService.getById(id), HttpStatus.OK);
    }


    @GetMapping(params = {"account"})
    public ResponseEntity getUserByAccount(@RequestParam("account") String account) {
        if ("".equals(account)) {
            return new ResponseEntity(ResponseBody.newErrorMessageBody(INVALID_FIELDS_REQUEST),
                    HttpStatus.BAD_REQUEST);
        }

        UserDto user = userService.getUserByAccount(account);
        ResponseBody<UserDto> responseBody = ResponseBody.newSingleBody(user);
        return new ResponseEntity(responseBody, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createUser(
            @Valid @RequestBody UserDto userDto,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Object errorMap = BindingResultHelper.toHashMap(bindingResult);
            return new ResponseEntity(
                    ResponseBody.newErrorMessageBody(INVALID_FIELDS_REQUEST, errorMap),
                    HttpStatus.BAD_REQUEST);
        }

        UserDto savedUserDto = userService.save(userDto);
        return new ResponseEntity(ResponseBody.newSingleBody(savedUserDto), HttpStatus.OK);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateUser(
            @PathVariable("id") UUID id,
            @Valid @RequestBody UserEditDto userEditDto,
            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            Object errorMap = BindingResultHelper.toHashMap(bindingResult);
            return new ResponseEntity(
                    ResponseBody.newErrorMessageBody(INVALID_FIELDS_REQUEST, errorMap),
                    HttpStatus.BAD_REQUEST);
        }

        UserDto updatedUserDto = userService.update(id, userEditDto);
        return new ResponseEntity(updatedUserDto, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable String id) {
        if ("".equals(id)) {
            return new ResponseEntity(ResponseBody.newErrorMessageBody(INVALID_FIELDS_REQUEST),
                    HttpStatus.BAD_REQUEST);
        }
        userService.delete(UUID.fromString(id));
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

    public static class SimpleQuery implements DtSpecification<User,UserDto> {
        private Integer id;
        private String userName;
        private String roles;

        @Override
        public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            List<Predicate> predicates = Lists.newArrayList();
            if (Objects.nonNull(id)) predicates.add(cb.equal(root.get("id"), id));
            if (Objects.nonNull(userName)) predicates.add(cb.equal(root.get("userName"), userName));
            if (Objects.nonNull(roles)) predicates.add(cb.equal(root.get("roles"), roles));

            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        }

        @Override
        public void setDataTablesInput(DataTablesInput input) {
            String idFilter = input.getColumn("id").getSearch().getValue();
            String nameFilter = input.getColumn("userName").getSearch().getValue();
            String rolesFilter = input.getColumn("roles").getSearch().getValue();
            id = (hasText(idFilter)) ? Integer.parseInt(idFilter) : null;
            userName = (hasText(nameFilter)) ? nameFilter : null;
            roles = (hasText(rolesFilter)) ? rolesFilter : null;
        }

        @Override
        public List<UserDto> setDataResults(List<User> input) {
            List<UserDto> result = new ArrayList<UserDto>();
            for (int i = 0; i < input.size(); i++) {
                User user = input.get(i);
                UserDto userDto = new UserDto();
                userDto.setId(user.getId());
                userDto.setUserName(user.getUserName());
                List<String> collect = user.getRoles().stream().map(role -> role.getName()).collect(Collectors.toList());
                userDto.setRoles(collect);
                result.add(userDto);
            }
            return result;
        }
    }
}
