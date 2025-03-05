package com.example.koeco_api.module.partner.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberCompanyRequest {
    @NotBlank(message = "Người đại diện không được để trống")
    private String representative;

    @NotBlank(message = "Tên công ty không được để trống")
    private String companyName;

    @Size(max = 13, message = "Số điện thoại không hợp lệ")
    private String phone;

    private String mainProduct;

    private String address;

    private String website;

    private MultipartFile logo;
}
