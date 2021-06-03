package indi.rennnhong.staterkit.rbac.web.controller;

import com.google.common.collect.Lists;
import indi.rennnhong.staterkit.rbac.service.RoleService;
import indi.rennnhong.staterkit.rbac.service.UserService;
import indi.rennnhong.staterkit.rbac.service.dto.RoleDto;
import indi.rennnhong.staterkit.rbac.service.dto.UserDto;
import indi.rennnhong.staterkit.rbac.web.command.UserCreateCommand;
import indi.rennnhong.staterkit.rbac.web.command.UserUpdateCommand;
import indi.rennnhong.staterkit.util.ThymeleafPathUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
@RequestMapping("users")
public class ViewController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Value("user")
    private String modulePath;

    @GetMapping
    public String getUserPage(Model model){
        String[] path = {"level1", "level2", "level3"};
        model.addAttribute("list", Lists.newArrayList(path));
        return ThymeleafPathUtils.buildViewPath("user", "home");
    }

    @GetMapping("/create")
    public String showCreateView(Model model) {
        UserCreateCommand formData = new UserCreateCommand();
        model.addAttribute("formData", formData);
        return ThymeleafPathUtils.buildFragmentPath(modulePath, "fragments/modal_forms", "form-create");
    }

    @GetMapping("{id}/update")
    public String showUpdateView(@PathVariable("id") UUID id, Model model) {
        UserDto user = userService.getById(id);
        UserUpdateCommand formData = new UserUpdateCommand();
        BeanUtils.copyProperties(user, formData);
        model.addAttribute("formData", formData);
        return ThymeleafPathUtils.buildFragmentPath(modulePath, "fragments/modal_forms", "form-update");
    }

    @GetMapping("/{id}/detail")
    public String showDetailView(@PathVariable("id") UUID id, Model model) {
        UserDto user = userService.getById(id);
        model.addAttribute("user", user);
        Collection<RoleDto> roleDtos = roleService.getAll();
        List<List<RoleDto>> roles = Lists.partition(roleDtos.stream().collect(Collectors.toList()), 2);
        model.addAttribute("roles",roles);
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
}
