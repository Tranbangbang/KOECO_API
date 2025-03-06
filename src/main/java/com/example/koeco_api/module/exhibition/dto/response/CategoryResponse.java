package com.example.koeco_api.module.exhibition.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponse {
    private Long id;

    private String nameKr;
    private String nameEn;
    private String nameCn;

    private Integer displayOrder;
}
