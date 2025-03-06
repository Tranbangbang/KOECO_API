package com.example.koeco_api.module.exhibition.dto.request.city;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityCreateRequest {
    private String nameKr;
    private String nameEn;
    private String nameCn;

    private Long idCountry;
}
