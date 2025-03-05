package com.example.koeco_api.module.user.controller;

import com.example.koeco_api.common.DefaultRes;
import com.example.koeco_api.common.ResponseMessage;
import com.example.koeco_api.common.StatusCode;
import com.example.koeco_api.module.user.model.ChangePasswordRequest;
import com.example.koeco_api.module.user.model.UserInfoReq;
import com.example.koeco_api.module.user.model.UserInfoRes;
import com.example.koeco_api.module.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Slf4j
@Tag(name = "02.User")
@RestController
@RequestMapping("/api/v1/rq/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PatchMapping("/changePassword")
    public ResponseEntity<?> changePassword(
            @Valid @RequestBody ChangePasswordRequest request,
            Principal connectedUser
    ) {
        service.changePassword(request, connectedUser);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER), HttpStatus.OK);
    }

    @GetMapping("/getUserInfo")
    public ResponseEntity<UserInfoRes> getUserInfo(
            Principal principal
    ) {
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.READ_USER, service.getUserInfo(principal)), HttpStatus.OK);
    }

    @PutMapping("/getUserInfo")
    public ResponseEntity<?> putUserInfo(
            @Valid @RequestBody UserInfoReq request, Principal principal
    ) {
        service.putUserInfo(request, principal);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER), HttpStatus.OK);
    }

    @DeleteMapping("/getUserInfo")
    public ResponseEntity<?> deleteUserInfo(
            Principal principal
    ) {
        service.deleteUserInfo(principal);
        return new ResponseEntity(DefaultRes.res(StatusCode.OK, ResponseMessage.UPDATE_USER), HttpStatus.OK);
    }
}
