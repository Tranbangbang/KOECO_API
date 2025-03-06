package com.example.koeco_api.module.exhibition.dto.request.exhibition;

import lombok.*;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExhibitionUpdateRequest {
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
    private File imageRegis;
    private File imageLarge;
    private List<File> documents;
    private List<Long> categoryIds;
}
