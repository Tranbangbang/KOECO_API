package com.example.koeco_api.module.exhibition.dto.response;

import lombok.*;
import org.hibernate.annotations.SecondaryRow;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityResponse {
    private Long id;
    private String nameKr;
    private String nameEn;
    private String nameCn;
}
