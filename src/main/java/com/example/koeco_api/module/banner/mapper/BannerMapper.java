package com.example.koeco_api.module.banner.mapper;

import com.example.koeco_api.module.banner.dto.BannerRequest;
import com.example.koeco_api.module.banner.dto.BannerResponse;
import com.example.koeco_api.module.banner.entity.Banner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class BannerMapper {
    public Banner toEntity(BannerRequest request) {
        return Banner.builder()
                .title(request.getTitle())
                .link(request.getLink())
                .pos(request.getPos())
                .displayStartDate(LocalDate.parse(request.getDisplayStartDate()))
                .displayEndDate(LocalDate.parse(request.getDisplayEndDate()))
                .build();
    }
    public BannerResponse toResponse(Banner entity) {
        return BannerResponse.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .imagePath(entity.getImagePath())
                .link(entity.getLink())
                .pos(entity.getPos())
                .displayStartDate(String.valueOf(entity.getDisplayStartDate()))
                .displayEndDate(String.valueOf(entity.getDisplayEndDate()))
                .build();
    }
}
