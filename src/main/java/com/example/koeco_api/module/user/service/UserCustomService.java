package com.example.koeco_api.module.user.service;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.config.JwtService;
import com.example.koeco_api.module.blacklist_token.dto.request.BlackTokenRequest;
import com.example.koeco_api.module.blacklist_token.service.IBlacklistService;
import com.example.koeco_api.module.user.common.Role;
import com.example.koeco_api.module.user.dto.request.*;
import com.example.koeco_api.module.user.dto.response.LoginResponse;
import com.example.koeco_api.module.user.dto.response.RefreshTokenResponse;
import com.example.koeco_api.module.user.dto.response.UserResponse;
import com.example.koeco_api.module.user.entity.User;
import com.example.koeco_api.module.user.mapper.UserMapper;
import com.example.koeco_api.module.user.repo.UserRepository;
import com.example.koeco_api.module.user_localizations.dto.response.UserLocalResponse;
import com.example.koeco_api.module.user_localizations.mapper.UserLocalMapper;
import com.example.koeco_api.module.user_localizations.service.IUserLocalService;
import com.example.koeco_api.module.user_localizations.service.UserLocalService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional
public class UserCustomService implements IUserCustomService{
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private IUserLocalService userLocalService;
    @Autowired
    IBlacklistService blacklistService;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    UserLocalMapper userLocalMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private HttpServletRequest httpRequest;


    @Override
    public Page<UserResponse> findUser(Pageable pageable, UserSearch userSearch) {
        userSearch.simpleValidate();
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

    @Override
    public LoginResponse login(LoginRequest request) {
        request.simpleValidate();

        User user = this.findByUserid(request.getUsername());
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new CommonException(ErrorCode.LOGIN_FAIL);

        List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().toString()));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User
                        (user.getUserId(), "", authorities);

        return LoginResponse.builder()
                .token(jwtService.generateToken(userDetails))
                .refreshToken(jwtService.generateRefreshToken(userDetails))
                .build();
    }

    @Override
    public void logout(LogoutRequest logoutRequest) {

        String token = jwtService.getToken(httpRequest);
        if(token == null || logoutRequest.getToken() == null )
            throw new CommonException(ErrorCode.TOKEN_NULL);

        //---blacklist token
       addToBlacklist(token);
       addToBlacklist(logoutRequest.getRefreshToken());
    }

    @Override
    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) {
        if(request.getRefreshToken() == null)
            throw new CommonException(ErrorCode.REFRESH_TOKEN_NULL);

        var authen = SecurityContextHolder.getContext().getAuthentication();
        if(authen == null || !authen.isAuthenticated())
            throw new CommonException(ErrorCode.USER_NOT_AUTHEN);

        var name = authen.getName();
        User user = this.findByUserid(name);

        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(user.getRole().toString()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
            name, "", authorities
        );

        if(!jwtService.validateToken(request.getRefreshToken(), userDetails))
            throw new CommonException(ErrorCode.REFRESH_TOKEN_NOT_VALID);

        return RefreshTokenResponse.builder()
                .token(jwtService.generateRefreshToken(userDetails))
                .build();
    }

    private void addToBlacklist(String token){
        if(token == null)
            return;

        var authen = SecurityContextHolder.getContext().getAuthentication();
        if(authen == null || !authen.isAuthenticated())
            return;

        if(!authen.getName().equals(jwtService.extractUsername(token)))
            throw new CommonException(ErrorCode.USER_NAME_TOKEN_NOT_MATCH);

        try {
            String id = jwtService.extractClaim(token, Claims::getId);
            Long expired = jwtService.extractClaim(token, claims -> claims.getExpiration().getTime());

            blacklistService.create(
                    BlackTokenRequest.builder()
                            .expired(expired)
                            .jwtId(id)
                            .build()
            );
        }catch (Exception e){
            log.error("err when add to black list: ", e.getMessage());
        }
    }
}
