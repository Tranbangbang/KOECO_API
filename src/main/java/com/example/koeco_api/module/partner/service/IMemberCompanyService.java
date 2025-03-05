package com.example.koeco_api.module.partner.service;

import com.example.koeco_api.module.partner.dto.request.MemberCompanyRequest;
import com.example.koeco_api.module.partner.dto.response.MemberCompanyResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IMemberCompanyService {
    MemberCompanyResponse createMemberCompany(MemberCompanyRequest request);
    Page<MemberCompanyResponse> getMemberCompanies(PageRequest pageRequest);
    MemberCompanyResponse getMemberCompanyDetail(Long id);
    MemberCompanyResponse updateMemberCompany(Long id, MemberCompanyRequest request);
    void deleteMemberCompany(Long id);
    Page<MemberCompanyResponse> searchMemberCompanies(String companyName, PageRequest pageRequest);
}
