package com.example.koeco_api.module.user.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LogoutRequest {
    String token;
    String refreshToken;
}
