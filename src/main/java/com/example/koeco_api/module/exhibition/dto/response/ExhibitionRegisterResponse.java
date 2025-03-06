package com.example.koeco_api.module.exhibition.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExhibitionRegisterResponse {
    private Long id;
    private String companyNameKr;
    private String companyNameEn;
    private String representativeKr;
    private String representativeEn;
    private String contactNameKr;
    private String contactNameEn;
    private String contactPhone;
    private String contactEmail;
    private String url;
    private String addressKr;
    private String addressEn;
    private String productNameKr;
    private String productNameEn;
    private String boothSize;
    private String businessRegistrationFilePath;
    private String corporateRegistrationFilePath;
    private LocalDateTime createDate;
    private String exhibitionName;
    private String applicationScale;
}
