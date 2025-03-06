package com.example.koeco_api.module.exhibition.dto.request.category;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryCreateRequest {
    private String nameKr;
    private String nameEn;
    private String nameCn;

    private Integer displayOrder;
}
