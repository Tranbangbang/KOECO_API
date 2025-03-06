package com.example.koeco_api.module.exhibition.dto.response;

import lombok.*;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExhibitionResponse {
    private String nameKr;
    private String nameEn;
    private String nameCn;
    private Boolean isHighlighted;
    private LocalDate startDate;
    private LocalDate endDate;
    private String website;
    private String videoUrl;
    private String boothFee;
    private String description;
    private String imageRegis;
    private String imageLarge;
    private List<String> documents;
    private List<CategoryResponse> categoryNames;
}
