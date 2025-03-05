package com.example.koeco_api.module.contact.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponse {
    private Long id;
    private String title;
    private String inquirerName;
    private String companyName;
    private String phoneNumber;
    private String email;
    private String address;
    private String content;
    private String captcha;
}
