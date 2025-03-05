package com.example.koeco_api.module.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "userRegisterReq")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterReq {
    @NotBlank
    @Size(min = 2, max = 8)
    private String userName;
    @NotBlank
    @Size(min = 5, max = 10)
    private String userId;
    @NotBlank
    private String password;
    private String mobileNum;
    private String email;
}
