package com.example.koeco_api.module.exhibition.dto.request.city;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityUpdateRequest {
    private String nameKr;
    private String nameEn;
    private String nameCn;
}
