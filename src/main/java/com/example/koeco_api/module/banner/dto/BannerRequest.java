package com.example.koeco_api.module.banner.dto;

import jakarta.validation.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BannerRequest {
    @NotBlank(message = "Tiêu đề không được để trống")
    private String title;

    private MultipartFile imagePath;

    private String link;

    private int pos;

    private String displayStartDate;

    private String displayEndDate;
}
