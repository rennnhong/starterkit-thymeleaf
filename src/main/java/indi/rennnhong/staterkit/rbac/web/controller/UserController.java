package indi.rennnhong.staterkit.rbac.web.controller;

import indi.rennnhong.staterkit.common.response.ResponseBody;
import indi.rennnhong.staterkit.rbac.service.UserService;
import indi.rennnhong.staterkit.rbac.service.dto.UserDto;
import indi.rennnhong.staterkit.rbac.service.dto.UserEditDto;
import indi.rennnhong.staterkit.common.query.PageableResult;
import indi.rennnhong.staterkit.common.utils.BindingResultHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;

import static indi.rennnhong.staterkit.common.response.ErrorMessages.INVALID_FIELDS_REQUEST;

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

}
