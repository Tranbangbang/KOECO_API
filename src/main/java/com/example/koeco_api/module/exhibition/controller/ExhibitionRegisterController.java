package com.example.koeco_api.module.exhibition.controller;

import com.example.koeco_api.common.DefaultRes;
import com.example.koeco_api.common.StatusCode;
import com.example.koeco_api.module.exhibition.dto.request.ExhibitionRegisterRequest;
import com.example.koeco_api.module.exhibition.dto.response.ExhibitionRegisterResponse;
import com.example.koeco_api.module.exhibition.service.IExhibitionRegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

@Tag(name = "06.ExhibitionRegister")
@RestController
@RequestMapping("/api/v1/exhibition-register")
@RequiredArgsConstructor
public class ExhibitionRegisterController {

    @Autowired
    IExhibitionRegisterService exhibitionRegisterService;

    @PostMapping("/create")
    public ResponseEntity<DefaultRes<ExhibitionRegisterResponse>> createExhibitionRegister(
            @Valid @RequestParam("exhibitionId") Long exhibitionId,
            @RequestParam("companyNameKr") String companyNameKr,
            @RequestParam("companyNameEn") String companyNameEn,
            @RequestParam("representativeKr") String representativeKr,
            @RequestParam("representativeEn") String representativeEn,
            @RequestParam("contactNameKr") String contactNameKr,
            @RequestParam("contactNameEn") String contactNameEn,
            @RequestParam("contactPhone") String contactPhone,
            @RequestParam("contactEmail") String contactEmail,
            @RequestParam("url") String url,
            @RequestParam("addressKr") String addressKr,
            @RequestParam("addressEn") String addressEn,
            @RequestParam("productNameKr") String productNameKr,
            @RequestParam("productNameEn") String productNameEn,
            @RequestParam("boothSize") String boothSize,
            @RequestParam("businessRegistrationFile") MultipartFile businessRegistrationFile,
            @RequestParam("corporateRegistrationFile") MultipartFile corporateRegistrationFile
    ) {

        ExhibitionRegisterRequest request = new ExhibitionRegisterRequest();
        request.setExhibitionId(exhibitionId);
        request.setCompanyNameKr(companyNameKr);
        request.setCompanyNameEn(companyNameEn);
        request.setRepresentativeKr(representativeKr);
        request.setRepresentativeEn(representativeEn);
        request.setContactNameKr(contactNameKr);
        request.setContactNameEn(contactNameEn);
        request.setContactPhone(contactPhone);
        request.setContactEmail(contactEmail);
        request.setUrl(url);
        request.setAddressKr(addressKr);
        request.setAddressEn(addressEn);
        request.setProductNameKr(productNameKr);
        request.setProductNameEn(productNameEn);
        request.setBoothSize(boothSize);
        request.setBusinessRegistrationFile(businessRegistrationFile);
        request.setCorporateRegistrationFile(corporateRegistrationFile);

        ExhibitionRegisterResponse response = exhibitionRegisterService.createExhibitionRegister(request);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.CREATED, "Đăng ký triển lãm thành công", response));
    }
}
