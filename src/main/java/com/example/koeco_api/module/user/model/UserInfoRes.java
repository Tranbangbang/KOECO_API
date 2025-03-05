package com.example.koeco_api.module.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserInfoRes {
    private String lastName;
    private String firstName;
    private String email;
}
