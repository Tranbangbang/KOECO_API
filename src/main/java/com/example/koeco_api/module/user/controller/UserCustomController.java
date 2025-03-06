package com.example.koeco_api.module.user.controller;

import com.example.koeco_api.common.DefaultRes;
import com.example.koeco_api.module.user.common.Role;
import com.example.koeco_api.module.user.dto.request.*;
import com.example.koeco_api.module.user.dto.response.LoginResponse;
import com.example.koeco_api.module.user.dto.response.RefreshTokenResponse;
import com.example.koeco_api.module.user.dto.response.UserResponse;
import com.example.koeco_api.module.user.service.IUserCustomService;
import com.example.koeco_api.utils.UtilsValue;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.models.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UtilsValue.BASE_URL + "/user/custom")
public class UserCustomController {
    @Autowired
    private IUserCustomService userCustomService;

    @GetMapping("/search")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(description = "List user")
    public DefaultRes<Page<UserResponse>> findAll(@RequestParam(value = "page", defaultValue = "1") Integer page,
                                                  @RequestParam(value = "limit", defaultValue = "10") Integer limit,
                                                  @RequestParam(value = "username", required = false) String username,
                                                  @RequestParam(value = "company", required = false) String company,
                                                  @RequestParam(value = "director", required = false) String director) {

        UserSearch userSearch = UserSearch.builder()
                .userName(username)
                .companyName(company)
                .director(director)
                .build();

        Pageable pageable = PageRequest.of(page - 1, limit);

        return DefaultRes.<Page<UserResponse>>builder()
                .statusCode(200)
                .data(userCustomService.findUser(pageable, userSearch))
                .build();
    }

    @PostMapping("/register")
    @Operation(description = "Register account")
    public DefaultRes<UserResponse> register(@RequestBody UserRegisterRequest request) {
        return DefaultRes.<UserResponse>builder()
                .data(userCustomService.register(request))
                .build();
    }

    @PostMapping("/login")
    @Operation(description = "login method")
    public DefaultRes<LoginResponse> login(@RequestBody LoginRequest request) {
        var data = userCustomService.login(request);
        return DefaultRes.<LoginResponse>builder()
                .statusCode(data != null ? 200 : 400)
                .data(data)
                .build();
    }

    @PostMapping("/logout")
    @Operation(description = "logout")
    public DefaultRes<String> logout(@RequestBody LogoutRequest request){
        userCustomService.logout(request);
        return DefaultRes.<String>builder()
                .statusCode(200)
                .data("Logout success")
                .build();
    }

    @PostMapping("/refresh_token")
    @Operation(description = "refresh_token")
    public DefaultRes<RefreshTokenResponse> refresh(@RequestBody RefreshTokenRequest request){
        return DefaultRes.<RefreshTokenResponse>builder()
                .statusCode(200)
                .data(userCustomService.refreshToken(request))
                .build();
    }

    @PutMapping("/update_admin")
    @Operation(description = "update infor admin")
    public DefaultRes<UserResponse> updateAdmin(@RequestBody AdminUpdateRequest request) {
        return DefaultRes.<UserResponse>builder()
                .data(userCustomService.adminUpdate(request))
                .build();
    }

}
