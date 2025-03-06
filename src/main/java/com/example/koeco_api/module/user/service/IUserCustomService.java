package com.example.koeco_api.module.user.service;

import com.example.koeco_api.module.user.dto.request.*;
import com.example.koeco_api.module.user.dto.response.LoginResponse;
import com.example.koeco_api.module.user.dto.response.RefreshTokenResponse;
import com.example.koeco_api.module.user.dto.response.UserResponse;
import com.example.koeco_api.module.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserCustomService {
    Page<UserResponse> findUser(Pageable pageable, UserSearch userSearch);
    User findByUserid(String id);
    UserResponse register(UserRegisterRequest request);
    UserResponse adminUpdate(AdminUpdateRequest request);
    LoginResponse login(LoginRequest request);
    void logout(LogoutRequest request);
    RefreshTokenResponse refreshToken(RefreshTokenRequest request);
}
