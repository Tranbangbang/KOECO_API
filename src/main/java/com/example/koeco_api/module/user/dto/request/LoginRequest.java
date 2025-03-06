package com.example.koeco_api.module.user.dto.request;

import com.example.koeco_api.utils.UtilsValidate;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {
    private String username;
    private String password;

    public void simpleValidate(){
        UtilsValidate.validateFields(this);
    }

}
