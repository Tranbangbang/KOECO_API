package com.example.koeco_api.module.blacklist_token.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlackListResponse {
    private Long id;
    private String jwtId;
    private Long expired;
}
