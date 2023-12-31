package com.ms.tourist_app.adapter.web.v1.controller;

import com.ms.tourist_app.adapter.web.base.ResponseUtil;
import com.ms.tourist_app.adapter.web.base.RestApiV1;
import com.ms.tourist_app.application.constants.UrlConst;
import com.ms.tourist_app.application.input.users.GetListUserInput;
import com.ms.tourist_app.application.input.users.UserDataInput;
import com.ms.tourist_app.application.mapper.UserMapper;
import com.ms.tourist_app.application.output.users.UserDataOutput;
import com.ms.tourist_app.application.service.UserService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestApiV1
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(UrlConst.User.users)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserDataInput input) {
        UserDataOutput output = userService.createUser(input);

        return ResponseUtil.restSuccess(output);
    }

    @GetMapping(UrlConst.User.users)
    //@PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getAllUser(GetListUserInput input){

        List<UserDataOutput> output = userService.getListUserOutPut(input);
        return ResponseUtil.restSuccess(output);
    }

    @GetMapping(UrlConst.User.getUserById)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> getUserData(@PathVariable(UrlConst.id)Long id){
        UserDataOutput userDataOutput = userService.getUserDataOutput(id);
        return ResponseUtil.restSuccess(userDataOutput);
    }

    @PutMapping(UrlConst.User.getUserById)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> updateUser(@PathVariable(UrlConst.id)Long id,@Valid @RequestBody UserDataInput input){

        UserDataOutput userDataOutput = userService.editUser(id,input);
        return ResponseUtil.restSuccess(userDataOutput);
    }

    @DeleteMapping(UrlConst.User.getUserById)
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable(UrlConst.id)Long id){
        UserDataOutput userDataOutput = userService.deleteUser(id);
        return ResponseUtil.restSuccess(userDataOutput);
    }




}


