package com.example.koeco_api.module.user.service;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.module.user.common.Role;
import com.example.koeco_api.module.user.dto.request.AdminUpdateRequest;
import com.example.koeco_api.module.user.dto.request.UserRegisterRequest;
import com.example.koeco_api.module.user.dto.request.UserSearch;
import com.example.koeco_api.module.user.dto.response.UserResponse;
import com.example.koeco_api.module.user.entity.User;
import com.example.koeco_api.module.user.mapper.UserMapper;
import com.example.koeco_api.module.user.repo.UserRepository;
import com.example.koeco_api.module.user_localizations.dto.response.UserLocalResponse;
import com.example.koeco_api.module.user_localizations.mapper.UserLocalMapper;
import com.example.koeco_api.module.user_localizations.service.IUserLocalService;
import com.example.koeco_api.module.user_localizations.service.UserLocalService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserCustomService implements IUserCustomService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserLocalService userLocalService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserLocalMapper userLocalMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Page<UserResponse> findUser(Pageable pageable, UserSearch userSearch) {
        return userRepository.findUser(pageable, userSearch.getUserName(), userSearch.getCompanyName(),
                userSearch.getDirector()).map(userMapper::convertToResponse);
    }

    @Override
    public User findByUserid(String id) {
        return userRepository.findByUserId(id).orElseThrow(
                () -> new CommonException(ErrorCode.USER_NOT_FOUND)
        );
    }

    @Override
    public UserResponse register(UserRegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail()))
            throw new CommonException(ErrorCode.EMAIL_WAS_REGISTER);

        var user = userMapper.convertToEntity(request);
        user.setRole(Role.USER);
        var userResponse = userMapper.convertToResponse(userRepository.save(user));

        List<UserLocalResponse> userLocalRes = new ArrayList<>();
        request.getInformation().forEach(
                it -> {
                    it.setUserId(request.getUserId());
                    userLocalRes.add(userLocalService.register(it));
                }
        );
        userResponse.setInformation(userLocalRes);
        return userResponse;
    }

    @Override
    public UserResponse adminUpdate(AdminUpdateRequest request) {
        var userAdmin = this.findByUserid("admin");

        userAdmin.setEmail(request.getEmail());
        userAdmin.setAlias(request.getAlias());
        userAdmin.setPassword(passwordEncoder.encode(request.getPassword()));

        return userMapper.convertToResponse(
                userRepository.save(userAdmin)
        );
    }
}
