package com.example.koeco_api.module.blacklist_token.dto.request;

import lombok.*;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlackTokenRequest {
    private String jwtId;
    private Long expired;
}
