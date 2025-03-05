package com.example.koeco_api.module.user.mapper;

import com.example.koeco_api.module.user.dto.request.UserRegisterRequest;
import com.example.koeco_api.module.user.dto.response.UserResponse;
import com.example.koeco_api.module.user.entity.User;
import com.example.koeco_api.module.user_localizations.mapper.UserLocalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserLocalMapper userLocalMapper;

    public User convertToEntity(UserRegisterRequest request) {
        return User.builder()
                .userId(request.getUserId())
                .alias(request.getAlias())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .build();
    }

    public UserResponse convertToResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .alias(user.getAlias())
                .email(user.getEmail())
                .information(
                        user.getUserLocalization() != null ?
                                user.getUserLocalization().stream().map(userLocalMapper::convertToResponse)
                                        .collect(Collectors.toList())
                                : Collections.emptyList()
                )
                .build();
    }
}
