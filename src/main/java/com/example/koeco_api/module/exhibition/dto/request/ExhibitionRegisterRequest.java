package com.example.koeco_api.module.exhibition.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExhibitionRegisterRequest {

    private Long exhibitionId;

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

    private String applicationScale;
    private String boothSize;

    private MultipartFile businessRegistrationFile;
    private MultipartFile corporateRegistrationFile;
}
