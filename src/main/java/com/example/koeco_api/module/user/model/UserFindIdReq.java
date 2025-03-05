package com.example.koeco_api.module.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Schema(name = "findIdReq")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserFindIdReq {
    @NotBlank
    @Size(min = 2, max = 8)
    private String userName;
    private String mobileNum;

}
