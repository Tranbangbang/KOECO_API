package com.example.koeco_api.module.user.dto.response;

import com.example.koeco_api.module.user_localizations.dto.request.UserLocalRegisRequest;
import com.example.koeco_api.module.user_localizations.dto.response.UserLocalResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {
    private String userId;
    private String alias;
    private String password;
    private String email;
    private List<UserLocalResponse> information;
}
