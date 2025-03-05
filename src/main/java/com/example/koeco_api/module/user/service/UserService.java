package com.example.koeco_api.module.user.service;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.module.user.common.Role;
import com.example.koeco_api.module.user.entity.User;
import com.example.koeco_api.module.user.model.ChangePasswordRequest;
import com.example.koeco_api.module.user.model.UserInfoReq;
import com.example.koeco_api.module.user.model.UserInfoRes;
import com.example.koeco_api.module.user.repo.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Slf4j
@Service
public class UserService implements LogoutHandler {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    UserRepository repository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository repository) {
        User checkExsit = repository.findByUserId("admin").orElse(null);
        if(checkExsit == null){
            User user = new User();
            user.setEmail("admin");
            user.setUserId("admin");
            user.setFirstName("admin");
            user.setLastName("admin");
            user.setRole(Role.ADMIN);
            user.setPassword(passwordEncoder.encode("admin"));
            repository.save(user);
        }
    }

    public void changePassword(ChangePasswordRequest request, Principal connectedUser) {
        try {
            var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
            if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
                throw new IllegalStateException("Wrong password");
            }
            if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
                throw new IllegalStateException("Password are not the same");
            }
            user.setPassword(passwordEncoder.encode(request.getNewPassword()));
            repository.save(user);
        } catch (Exception e) {
            log.error("Error changing password", e);
            throw new RuntimeException("Error changing password", e);
        }
    }

    @Override
    public void logout(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) {
        final String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
    }

    public UserInfoRes getUserInfo(Principal principal) {
        try {
            User nowUser = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            User userFromRepository = repository.findByUserId(nowUser.getUserId())
                    .orElseThrow(() -> new CommonException(ErrorCode.ID_NOT_FOUND));
            return UserInfoRes.builder()
                    .email(userFromRepository.getEmail())
                    .firstName(userFromRepository.getFirstName())
                    .lastName(userFromRepository.getLastName())
                    .build();
        } catch (Exception e) {
            log.error("Error getting user information", e);
            throw new RuntimeException("Error getting user information", e);
        }
    }

    public void putUserInfo(UserInfoReq request, Principal principal) {
        try {
            User nowUser = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            User updatedUser = repository.findByUserId(nowUser.getUserId())
                    .map(user -> {
                        user.setLastName(request.getLastName());
                        user.setFirstName(request.getFirstName());
                        return user;
                    })
                    .orElseThrow(() -> new CommonException(ErrorCode.ID_NOT_FOUND));
            repository.save(updatedUser);
        } catch (Exception e) {
            log.error("", e);
            throw new RuntimeException("Error updating user information", e);
        }
    }

    public void deleteUserInfo(Principal principal) {
        try {
            User nowUser = (User) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
            User userToDelete = repository.findByUserId(nowUser.getUserId())
                    .orElseThrow(() -> new CommonException(ErrorCode.ID_NOT_FOUND));
            repository.delete(userToDelete);
        } catch (Exception e) {
            log.error("Error deleting user information", e);
            throw new RuntimeException("Error deleting user information", e);
        }
    }
}
