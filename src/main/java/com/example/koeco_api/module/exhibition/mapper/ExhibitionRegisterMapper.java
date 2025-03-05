package com.example.koeco_api.module.exhibition.mapper;

import com.example.koeco_api.module.exhibition.dto.request.ExhibitionRegisterRequest;
import com.example.koeco_api.module.exhibition.dto.response.ExhibitionRegisterResponse;
import com.example.koeco_api.module.exhibition.entity.ExhibitionRegister;
import org.springframework.stereotype.Component;

@Component
public class ExhibitionRegisterMapper {

    public ExhibitionRegister toEntity(ExhibitionRegisterRequest request) {
        return ExhibitionRegister.builder()
                .companyNameKr(request.getCompanyNameKr())
                .companyNameEn(request.getCompanyNameEn())
                .representativeKr(request.getRepresentativeKr())
                .representativeEn(request.getRepresentativeEn())
                .contactNameKr(request.getContactNameKr())
                .contactNameEn(request.getContactNameEn())
                .contactPhone(request.getContactPhone())
                .contactEmail(request.getContactEmail())
                .url(request.getUrl())
                .addressKr(request.getAddressKr())
                .addressEn(request.getAddressEn())
                .productNameKr(request.getProductNameKr())
                .productNameEn(request.getProductNameEn())
                .boothSize(request.getBoothSize())
                .build();
    }

    public ExhibitionRegisterResponse toResponse(ExhibitionRegister entity) {
        return ExhibitionRegisterResponse.builder()
                .id(entity.getId())
                .companyNameKr(entity.getCompanyNameKr())
                .companyNameEn(entity.getCompanyNameEn())
                .representativeKr(entity.getRepresentativeKr())
                .representativeEn(entity.getRepresentativeEn())
                .contactNameKr(entity.getContactNameKr())
                .contactNameEn(entity.getContactNameEn())
                .contactPhone(entity.getContactPhone())
                .contactEmail(entity.getContactEmail())
                .url(entity.getUrl())
                .addressKr(entity.getAddressKr())
                .addressEn(entity.getAddressEn())
                .productNameKr(entity.getProductNameKr())
                .productNameEn(entity.getProductNameEn())
                .boothSize(entity.getBoothSize())
                .businessRegistrationFilePath(entity.getBusinessRegistrationFilePath())
                .corporateRegistrationFilePath(entity.getCorporateRegistrationFilePath())
                .build();
    }
}
