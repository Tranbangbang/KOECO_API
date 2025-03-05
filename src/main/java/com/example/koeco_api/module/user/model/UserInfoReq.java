package com.example.koeco_api.module.user.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserInfoReq {
    @NotBlank
    private String lastName;
    @NotBlank
    private String firstName;
    private String mobileNum;
}
