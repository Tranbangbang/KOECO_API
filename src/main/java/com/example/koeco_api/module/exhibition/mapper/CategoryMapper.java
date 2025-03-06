package com.example.koeco_api.module.exhibition.mapper;

import com.example.koeco_api.module.exhibition.dto.request.category.CategoryCreateRequest;
import com.example.koeco_api.module.exhibition.dto.response.CategoryResponse;
import com.example.koeco_api.module.exhibition.entity.Category;
import lombok.*;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    public Category convertToEntity(CategoryCreateRequest request){
        return Category.builder()
                .nameKr(request.getNameKr())
                .nameEn(request.getNameEn())
                .nameCn(request.getNameCn())
                .displayOrder(request.getDisplayOrder())
                .build();
    }

    public CategoryResponse convertToResponse(Category entity){
        return CategoryResponse.builder()
                .id(entity.getId())
                .nameKr(entity.getNameKr())
                .nameEn(entity.getNameEn())
                .nameCn(entity.getNameCn())
                .displayOrder(entity.getDisplayOrder())
                .build();
    }
}
