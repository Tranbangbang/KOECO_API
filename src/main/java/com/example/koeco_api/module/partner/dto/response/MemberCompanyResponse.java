package com.example.koeco_api.module.partner.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCompanyResponse {
    private Long id;
    private String representative;
    private String companyName;
    private String phone;
    private String mainProduct;
    private String address;
    private String website;
    private String logoUrl;
}
