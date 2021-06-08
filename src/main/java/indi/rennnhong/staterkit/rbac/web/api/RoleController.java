//package indi.rennnhong.staterkit.rbac.web.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import indi.rennnhong.staterkit.common.response.ResponseBody;
//import indi.rennnhong.staterkit.rbac.service.PermissionService;
//import indi.rennnhong.staterkit.rbac.service.RoleService;
//import indi.rennnhong.staterkit.rbac.service.UserService;
//import indi.rennnhong.staterkit.rbac.service.dto.RoleDto;
//import indi.rennnhong.staterkit.rbac.service.dto.RoleEditDto;
//import indi.rennnhong.staterkit.rbac.service.dto.UserDto;
//import indi.rennnhong.staterkit.common.query.PageableResult;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Collection;
//import java.util.UUID;
//
//
//@RestController
//@RequestMapping("/api/roles")
//public class RoleController {
//
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @Autowired
//    private RoleService roleService;
//
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    private PermissionService permissionService;
//
//    @GetMapping
//    public ResponseEntity getRoles(@RequestParam(defaultValue = "1") Integer pageNumber,
//                                   @RequestParam(defaultValue = "10") Integer rowsPerPage) {
//        PageableResult<RoleDto> roles = roleService.pageAll(pageNumber, rowsPerPage);
//        ResponseBody<Collection<RoleDto>> responseBody = ResponseBody.newPageableBody(roles);
//        return ResponseEntity.ok(responseBody);
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity getRoleById(@PathVariable UUID id) {
//        RoleDto role = roleService.getById(id);
//        ResponseBody<RoleDto> responseBody = ResponseBody.newSingleBody(role);
//        return ResponseEntity.ok(responseBody);
//    }
//
//    @GetMapping("/{roleId}/users")
//    public ResponseEntity getUsersOfRole(@PathVariable UUID roleId) {
//        Collection<UserDto> usersOfRole = userService.getUsersOfRole(roleId);
//        ResponseBody<Collection<UserDto>> responseBody = ResponseBody.newCollectionBody(usersOfRole);
//        return new ResponseEntity(responseBody, HttpStatus.OK);
//    }
//
//
//    @PostMapping
//    public ResponseEntity createRole(@RequestBody RoleDto roleDto) {
//        RoleDto savedRole = roleService.save(roleDto);
//        ResponseBody<RoleDto> responseBody = ResponseBody.newSingleBody(savedRole);
//        return new ResponseEntity(responseBody, HttpStatus.CREATED);
//    }
//
//
//    @PutMapping("/{id}")
//    public ResponseEntity updateRole(@PathVariable("id") UUID id,
//                                     @RequestBody RoleEditDto roleEditDto) {
//        RoleDto updatedRole = roleService.update(id, roleEditDto);
//        ResponseBody<RoleDto> responseBody = ResponseBody.newSingleBody(updatedRole);
//        return ResponseEntity.ok(responseBody);
//    }
//
////    @PutMapping("/{id}/permission")
////    @ApiOperation("修改角色資料")
////    public ResponseEntity updateRolePermission(@PathVariable("id") UUID id,
////                                               @RequestBody UpdateRoleRequestDto updateRoleRequestDto) {
////        RoleDto updatedRole = roleService.updateRolePermission(id, updateRoleRequestDto);
////        ResponseBody<RoleDto> responseBody = ResponseBody.newSingleBody(updatedRole);
////        return ResponseEntity.ok(responseBody);
////    }
//
//
//    @DeleteMapping("/{id}")
//    public ResponseEntity deleteRole(@PathVariable UUID id) {
////        if (!roleService.isExist(id)) {
////            return new ResponseEntity(ResponseBody.newErrorMessageBody(RESOURCE_NOT_FOUND), HttpStatus.NOT_FOUND);
////        }
//
////        if (roleService.isRoleReferenced(id)) {
////            //todo 若有使用者還在用該角色，拋出異常
////            return new ResponseEntity(ResponseBody.newSingleBody("該角色目前還有其他使用者引用"), HttpStatus.BAD_REQUEST);
////        }
//
//        roleService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//
////    @GetMapping("/auths")
////    @ApiOperation("取得所有頁面權限角色資料")
////    public ResponseEntity getPermissionsOfUser() {
////        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////        String userId = authentication.getName();
////        if (!userService.isExist(UUID.fromString(userId))) {
////            return new ResponseEntity(ResponseBody.newErrorMessageBody(RESOURCE_NOT_FOUND), HttpStatus.NOT_FOUND);
////        }
////        Set<RoleDto> roles = roleService.getRolesByUserId(UUID.fromString(userId));
////        Collection<PermissionDto> permissions = permissionService.getPermissionsByRoles(roles);
////        ResponseBody<Collection<PermissionDto>> responseBody = ResponseBody.newCollectionBody(permissions);
////        return new ResponseEntity(responseBody, HttpStatus.OK);
////    }
//
//
//}
