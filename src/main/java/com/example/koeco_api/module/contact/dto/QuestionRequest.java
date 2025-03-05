package com.example.koeco_api.module.contact.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionRequest {
    @NotNull
    private String title;

    private String inquirerName;

    private String companyName;

    private String phoneNumber;

    private String email;

    private String address;

    @NotNull
    private String content;

    @NotNull(message = "CAPTCHA không được để trống")
    private String captcha;
}
