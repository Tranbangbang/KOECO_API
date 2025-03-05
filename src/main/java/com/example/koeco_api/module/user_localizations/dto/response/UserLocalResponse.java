package com.example.koeco_api.module.user_localizations.dto.response;

import com.example.koeco_api.enums.LanguageCodeEnum;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLocalResponse {
    private Long id;
    private LanguageCodeEnum languageCode;
    private String companyName;
    private String companyDescription;
    private String companyField;
    private String address;
    private String director;
    private String userId;
}
