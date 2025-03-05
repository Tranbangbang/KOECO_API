package com.example.koeco_api.module.banner.controller;

import com.example.koeco_api.common.DefaultRes;
import com.example.koeco_api.common.StatusCode;
import com.example.koeco_api.module.banner.dto.BannerRequest;
import com.example.koeco_api.module.banner.dto.BannerResponse;
import com.example.koeco_api.module.banner.service.IBannerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "05.Banner")
@RestController
@RequestMapping("/api/v1/banner")
@RequiredArgsConstructor
public class BannerController {

    @Autowired
    IBannerService bannerService;

    @PostMapping("/create")
    public ResponseEntity<DefaultRes<BannerResponse>> createBanner(@Valid @ModelAttribute BannerRequest request) {
        BannerResponse response = bannerService.createBanner(request);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.CREATED, "Thêm banner thành công", response));
    }

    @GetMapping("/list")
    public ResponseEntity<DefaultRes<Page<BannerResponse>>> getBannerList(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<BannerResponse> response = bannerService.getBannerList(PageRequest.of(page, size));
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Danh sách banner", response));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultRes<BannerResponse>> getBannerDetail(@PathVariable Long id) {
        BannerResponse response = bannerService.getBannerDetail(id);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Chi tiết banner", response));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DefaultRes<BannerResponse>> updateBanner(@PathVariable Long id, @Valid @ModelAttribute BannerRequest request) {
        BannerResponse response = bannerService.updateBanner(id, request);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Cập nhật banner thành công", response));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DefaultRes<Void>> deleteBanner(@PathVariable Long id) {
        bannerService.deleteBanner(id);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Xóa banner thành công"));
    }
}
