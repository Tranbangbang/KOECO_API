package com.example.koeco_api.module.exhibition.dto.request.country;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryCreate {
    private String nameKr;
    private String nameEn;
    private String nameCn;
}
