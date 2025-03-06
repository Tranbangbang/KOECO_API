package com.example.koeco_api.module.exhibition.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExhibitionRegisterUpdateRequest {
    private String applicationScale;
    private String boothSize;
}
