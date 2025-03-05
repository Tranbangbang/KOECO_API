package com.example.koeco_api.module.banner.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerResponse {
    private Long id;
    private String title;
    private String imagePath;
    private String link;
    private int pos;
    private String displayStartDate;
    private String displayEndDate;
}
