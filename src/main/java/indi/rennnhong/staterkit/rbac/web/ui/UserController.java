package indi.rennnhong.staterkit.rbac.web.ui;

import com.google.common.collect.Lists;
import indi.rennnhong.staterkit.common.web.Response;
import indi.rennnhong.staterkit.common.web.support.DtSpecification;
import indi.rennnhong.staterkit.rbac.entity.User;
import indi.rennnhong.staterkit.rbac.service.RoleService;
import indi.rennnhong.staterkit.rbac.service.UserService;
import indi.rennnhong.staterkit.rbac.service.dto.RoleDto;
import indi.rennnhong.staterkit.rbac.service.dto.UserDto;
import indi.rennnhong.staterkit.rbac.service.dto.UserEditDto;
import indi.rennnhong.staterkit.rbac.web.command.UserCreateCommand;
import indi.rennnhong.staterkit.rbac.web.command.UserUpdateCommand;
import indi.rennnhong.staterkit.util.RouteUtils;
import indi.rennnhong.staterkit.util.ThymeleafPathUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Value("user")
    private String modulePath;

    @GetMapping
    public String getUserPage(Model model) {
        String[] path = {"level1", "level2", "level3"};
        model.addAttribute("list", Lists.newArrayList(path));
        return ThymeleafPathUtils.buildViewPath("user", "home");
    }

    @GetMapping("/create")
    public String showCreateView(Model model) {
        UserCreateCommand userCreateCommand = new UserCreateCommand();
        model.addAttribute("user", userCreateCommand);
        Collection<RoleDto> roleDtos = roleService.getAll();
        List<List<RoleDto>> roleList = Lists.partition(roleDtos.stream().collect(Collectors.toList()), 2);
        model.addAttribute("roleList", roleList);
        return ThymeleafPathUtils.buildFragmentPath(modulePath, "fragments/modal_forms", "form-create");
    }

    @GetMapping("{id}/update")
    public String showUpdateView(@PathVariable("id") UUID id, Model model) {
        UserDto user = userService.getById(id);
        model.addAttribute("user", user);
        List<List<RoleDto>> roleList = getRoleOptionGroups();
        model.addAttribute("roleList", roleList);
        return ThymeleafPathUtils.buildFragmentPath(modulePath, "fragments/modal_forms", "form-update");
    }

    @GetMapping("/{id}/detail")
    public String showDetailView(@PathVariable("id") UUID id, Model model) {
        UserDto user = userService.getById(id);
        model.addAttribute("user", user);
        List<List<RoleDto>> roleList = getRoleOptionGroups();
        model.addAttribute("roleList", roleList);
        String s = ThymeleafPathUtils.buildFragmentPath(modulePath, "fragments/modal_forms", "form-detail");
        return ThymeleafPathUtils.buildFragmentPath(modulePath, "fragments/modal_forms", "form-detail");
    }

    @GetMapping("{id}/delete")
    public String showDeletedView(@PathVariable("id") UUID id, Model model) {
        UserDto user = userService.getById(id);
        UserUpdateCommand formData = new UserUpdateCommand();
        BeanUtils.copyProperties(user, formData);
        model.addAttribute("formData", formData);
        return ThymeleafPathUtils.buildFragmentPath(modulePath, "fragments/modal_forms", "form-delete");
    }

    @PostMapping
    public String doCreate(
            HttpServletRequest request,
            @Valid @ModelAttribute("user") UserCreateCommand formData,
            BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(formData, userDto);
            UserDto userDtoSave = userService.save(userDto);
            request.setAttribute("payload", userDtoSave.getId());
            return RouteUtils.forward("user", RouteUtils.Route.CREATE_SUCCESS);
        }
        List<List<RoleDto>> roleList = getRoleOptionGroups();
        model.addAttribute("roleList", roleList);
        return ThymeleafPathUtils.buildFragmentPath(modulePath, "fragments/modal_forms", "form-create");
    }

    @PutMapping("/{id}")
    public String updateUser(
            HttpServletRequest request,
            @PathVariable("id") UUID id,
            @Valid @ModelAttribute("user") UserUpdateCommand userUpdateCommand,
            BindingResult bindingResult, Model model) {
        if (!bindingResult.hasErrors()) {
            UserEditDto userEditDto = new UserEditDto();
            BeanUtils.copyProperties(userUpdateCommand, userEditDto);
            UserDto updatedUserDto = userService.update(id, userEditDto);
            request.setAttribute("payload",updatedUserDto.getId());
            return RouteUtils.forward("user", RouteUtils.Route.UPDATE_SUCCESS);
        }
        List<List<RoleDto>> roleList = getRoleOptionGroups();
        model.addAttribute("roleList", roleList);
        return ThymeleafPathUtils.buildFragmentPath(modulePath, "fragments/modal_forms", "form-update");
    }

    @org.springframework.web.bind.annotation.ResponseBody
    @DeleteMapping(value = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Response doDeleted(@PathVariable("id") UUID id) {
        userService.delete(id);
        return Response.ok().setPayload(id);
    }

    private List<RoleDto> getRoleOptions() {
        return roleService.getAll().stream().collect(Collectors.toList());
    }

    private List<List<RoleDto>> getRoleOptionGroups() {
        return Lists.partition(getRoleOptions(), 2);
    }

    public static class SimpleQuery implements DtSpecification<User, UserDto> {
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
