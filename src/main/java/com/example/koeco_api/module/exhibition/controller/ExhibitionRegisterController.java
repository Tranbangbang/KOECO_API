package com.example.koeco_api.module.exhibition.controller;

import com.example.koeco_api.common.DefaultRes;
import com.example.koeco_api.common.StatusCode;
import com.example.koeco_api.module.exhibition.dto.request.ExhibitionRegisterRequest;
import com.example.koeco_api.module.exhibition.dto.request.ExhibitionRegisterUpdateRequest;
import com.example.koeco_api.module.exhibition.dto.response.ExhibitionRegisterResponse;
import com.example.koeco_api.module.exhibition.service.IExhibitionRegisterService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

@Tag(name = "05.ExhibitionRegister")
@RestController
@RequestMapping("/api/v1/exhibition-register")
@RequiredArgsConstructor
public class ExhibitionRegisterController {

    @Autowired
    IExhibitionRegisterService exhibitionRegisterService;

    @PostMapping("/create")
    public ResponseEntity<DefaultRes<ExhibitionRegisterResponse>> createExhibitionRegister(
         @ModelAttribute ExhibitionRegisterRequest exhibitionRegisterRequest
    ) {
        ExhibitionRegisterResponse response = exhibitionRegisterService.createExhibitionRegister(exhibitionRegisterRequest);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.CREATED, "Đăng ký triển lãm thành công", response));
    }

    @GetMapping
    public ResponseEntity<DefaultRes<Page<ExhibitionRegisterResponse>>> getAllExhibitionRegisters(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        Page<ExhibitionRegisterResponse> response = exhibitionRegisterService.getAllExhibitionRegisters(page, size);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Lấy danh sách thành công", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultRes<ExhibitionRegisterResponse>> getExhibitionRegisterDetail(@PathVariable Long id) {
        ExhibitionRegisterResponse response = exhibitionRegisterService.getExhibitionRegister(id);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Lấy chi tiết thành công", response));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DefaultRes<ExhibitionRegisterResponse>> updateExhibitionRegister(
            @PathVariable Long id,
            @RequestBody ExhibitionRegisterUpdateRequest updateRequest) {

        ExhibitionRegisterResponse response = exhibitionRegisterService.updateExhibitionRegister(id, updateRequest);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Cập nhật thành công", response));
    }


}
