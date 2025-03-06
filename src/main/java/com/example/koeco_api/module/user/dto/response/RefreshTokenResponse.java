package com.example.koeco_api.module.user.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenResponse {
    private String token;
}
