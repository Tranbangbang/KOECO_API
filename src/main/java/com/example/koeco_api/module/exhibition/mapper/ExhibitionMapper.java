package com.example.koeco_api.module.exhibition.mapper;

import com.example.koeco_api.module.exhibition.dto.request.exhibition.ExhibitionCreateRequest;
import com.example.koeco_api.module.exhibition.dto.response.ExhibitionResponse;
import com.example.koeco_api.module.exhibition.entity.Exhibition;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class ExhibitionMapper {
    public Exhibition convertToEntity(ExhibitionCreateRequest request) {
        return Exhibition.builder()
                .nameKr(request.getNameKr())
                .nameEn(request.getNameEn())
                .nameCn(request.getNameCn())
                .isHighlighted(request.getIsHighlighted())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .website(request.getWebsite())
                .videoUrl(request.getVideoUrl())
                .boothFee(request.getBoothFee())
                .description(request.getDescription())
                .build();
    }

    public ExhibitionResponse convertToResponse(Exhibition entity) {
        return ExhibitionResponse.builder()
                .nameKr(entity.getNameKr())
                .nameEn(entity.getNameEn())
                .nameCn(entity.getNameCn())
                .isHighlighted(entity.getIsHighlighted())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .website(entity.getWebsite())
                .videoUrl(entity.getVideoUrl())
                .boothFee(entity.getBoothFee())
                .description(entity.getDescription())

                .imageRegis(entity.getImageRegis())
                .imageLarge(entity.getImageLarge())
                .documents(
                        entity.getExhibitionDocuments() != null ?
                                entity.getExhibitionDocuments().stream().map(it -> it.getDocumentPath()).toList()
                                : Collections.emptyList()
                )
                .build();
    }
}
