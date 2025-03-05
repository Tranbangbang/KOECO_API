package com.example.koeco_api.module.partner.mapper;

import com.example.koeco_api.module.partner.dto.request.MemberCompanyRequest;
import com.example.koeco_api.module.partner.dto.response.MemberCompanyResponse;
import com.example.koeco_api.module.partner.entity.MemberCompany;
import org.springframework.stereotype.Component;

@Component
public class MemberCompanyMapper {
    public MemberCompany toEntity(MemberCompanyRequest request, String logoUrl) {
        return MemberCompany.builder()
                .representative(request.getRepresentative())
                .companyName(request.getCompanyName())
                .phone(request.getPhone())
                .mainProduct(request.getMainProduct())
                .address(request.getAddress())
                .website(request.getWebsite())
                .logoUrl(logoUrl)
                .build();
    }

    public MemberCompanyResponse toResponse(MemberCompany entity) {
        return MemberCompanyResponse.builder()
                .id(entity.getId())
                .representative(entity.getRepresentative())
                .companyName(entity.getCompanyName())
                .phone(entity.getPhone())
                .mainProduct(entity.getMainProduct())
                .address(entity.getAddress())
                .website(entity.getWebsite())
                .logoUrl(entity.getLogoUrl())
                .build();
    }

}
