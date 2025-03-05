package com.example.koeco_api.module.user.service;

import com.example.koeco_api.module.user.dto.request.UserRegisterRequest;
import com.example.koeco_api.module.user.dto.request.AdminUpdateRequest;
import com.example.koeco_api.module.user.dto.request.UserSearch;
import com.example.koeco_api.module.user.dto.response.UserResponse;
import com.example.koeco_api.module.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IUserCustomService {
    Page<UserResponse> findUser(Pageable pageable, UserSearch userSearch);
    User findByUserid(String id);
    UserResponse register(UserRegisterRequest request);
    UserResponse adminUpdate(AdminUpdateRequest request);
}
