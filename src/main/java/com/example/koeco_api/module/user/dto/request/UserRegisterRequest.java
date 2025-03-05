package com.example.koeco_api.module.user.dto.request;

import com.example.koeco_api.module.user_localizations.dto.request.UserLocalRegisRequest;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequest {
    private String userId;
    private String alias;
    private String password;
    private String email;
    private List<UserLocalRegisRequest> information;
}
