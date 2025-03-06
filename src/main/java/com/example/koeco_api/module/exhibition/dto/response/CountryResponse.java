package com.example.koeco_api.module.exhibition.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountryResponse {
    private Long id;

    private String nameKr;
    private String nameEn;
    private String nameCn;

    private List<CityResponse> cities;
}
