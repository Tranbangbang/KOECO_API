package com.example.koeco_api.module.banner.service;

import com.example.koeco_api.module.banner.dto.BannerRequest;
import com.example.koeco_api.module.banner.dto.BannerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface IBannerService {
    BannerResponse createBanner(BannerRequest request);

    Page<BannerResponse> getBannerList(PageRequest pageRequest);

    BannerResponse getBannerDetail(Long id);

    BannerResponse updateBanner(Long id, BannerRequest request);

    void deleteBanner(Long id);
}
