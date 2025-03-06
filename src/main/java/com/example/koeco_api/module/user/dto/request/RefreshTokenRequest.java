package com.example.koeco_api.module.user.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenRequest {
    private String refreshToken;
}
