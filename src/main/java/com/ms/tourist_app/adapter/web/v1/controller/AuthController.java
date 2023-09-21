package com.ms.tourist_app.adapter.web.v1.controller;

import com.ms.tourist_app.adapter.web.base.ResponseUtil;
import com.ms.tourist_app.adapter.web.base.RestApiV1;
import com.ms.tourist_app.adapter.web.v1.transfer.parameter.auth.AuthenticationRequest;
import com.ms.tourist_app.application.constants.UrlConst;
import com.ms.tourist_app.application.input.users.UserDataInput;
import com.ms.tourist_app.application.mapper.UserMapper;
import com.ms.tourist_app.application.service.AuthService;
import org.mapstruct.factory.Mappers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@RestApiV1
public class AuthController {
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    private final AuthService authService;

    @PostMapping(UrlConst.Auth.login)
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authenticationRequest){
        authService.login(authenticationRequest);
        return ResponseUtil.restSuccess(authService.login(authenticationRequest));
    }

    @PostMapping(UrlConst.Auth.signUp)
    public ResponseEntity<?> signUpUser( @RequestBody UserDataInput input){
        return ResponseUtil.restSuccess(authService.signupUser(input));
    }
    @PostMapping(UrlConst.Auth.signUp+"/admin")
    public ResponseEntity<?> signUpAdmin( @RequestBody UserDataInput input){
        return ResponseUtil.restSuccess(authService.signupAdmin(input));
    }
}
