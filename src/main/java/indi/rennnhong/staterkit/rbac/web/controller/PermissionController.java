package indi.rennnhong.staterkit.rbac.web.controller;

import indi.rennnhong.staterkit.common.response.ResponseBody;
import indi.rennnhong.staterkit.rbac.service.ActionService;
import indi.rennnhong.staterkit.rbac.service.PermissionService;
import indi.rennnhong.staterkit.rbac.service.dto.ActionDto;
import indi.rennnhong.staterkit.rbac.service.dto.ActionEditDto;
import indi.rennnhong.staterkit.rbac.service.dto.PermissionDto;
import indi.rennnhong.staterkit.rbac.service.dto.PermissionEditDto;
import indi.rennnhong.staterkit.common.query.PageableResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    PermissionService permissionService;

    @Autowired
    ActionService actionService;

    @GetMapping
    public ResponseEntity getPermissions(@RequestParam(defaultValue = "1") Integer pageNumber,
                                         @RequestParam(defaultValue = "10") Integer rowsPerPage) {
        PageableResult<PermissionDto> result = permissionService.pageAll(pageNumber, rowsPerPage);
        return new ResponseEntity(ResponseBody.newPageableBody(result), HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity getPermission(@PathVariable UUID id) {
        PermissionDto permission = permissionService.getById(id);
        return new ResponseEntity(ResponseBody.newSingleBody(permission), HttpStatus.OK);
    }


    @GetMapping("/{permissionId}/actions")
    public ResponseEntity<?> getActionsOfPermission(@PathVariable String permissionId) {
        Collection<ActionDto> actions = actionService.getActionsByPermissionId(permissionId);
        ResponseBody<Collection<ActionDto>> responseBody = ResponseBody.newCollectionBody(actions);
        return ResponseEntity.ok(responseBody);
    }

    @PostMapping
    public ResponseEntity<?> createPermission(@RequestBody PermissionDto permissionDto) {
        PermissionDto permission = permissionService.save(permissionDto);
        ResponseBody<PermissionDto> responseBody = ResponseBody.newSingleBody(permission);
        return new ResponseEntity(responseBody, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity updatePermission(@PathVariable UUID id,
                                           @RequestBody PermissionEditDto permissionEditDto) {
        PermissionDto updatedPermission = permissionService.update(id, permissionEditDto);
//        if (ObjectUtils.isEmpty(updatedPermission)) {
//            return new ResponseEntity(ErrorMessages.RESOURCE_NOT_FOUND.toObject(), HttpStatus.NOT_FOUND);
//        }
        ResponseBody<PermissionDto> responseBody = ResponseBody.newSingleBody(updatedPermission);
        return new ResponseEntity(responseBody, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePermission(@PathVariable UUID id) {
        permissionService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{pageId}/actions")
    public ResponseEntity createAction(@PathVariable UUID pageId,
                                       @RequestBody ActionDto actionDto) {
        ActionDto savedAction = actionService.save(pageId,actionDto);
        ResponseBody<ActionDto> responseBody = ResponseBody.newSingleBody(savedAction);
        return new ResponseEntity(responseBody, HttpStatus.CREATED);
    }


    @PutMapping("/{permissionId}/actions/{actionId}")
    public ResponseEntity updateAction(@PathVariable UUID permissionId,
                                       @PathVariable UUID actionId,
                                       @RequestBody ActionEditDto actionEditDto) {
        ActionDto updatedAction = actionService.update(actionId, permissionId, actionEditDto);
        ResponseBody<ActionDto> responseBody = ResponseBody.newSingleBody(updatedAction);
        return ResponseEntity.ok(responseBody);
    }


    @DeleteMapping("/actions/{actionId}")
    public ResponseEntity<?> deleteAction(@PathVariable UUID actionId, @PathVariable UUID pageId) {
        actionService.delete(actionId);
        return ResponseEntity.noContent().build();
    }


}
