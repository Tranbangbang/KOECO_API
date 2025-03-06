package com.example.koeco_api.module.exhibition.dto.request.country;

import com.example.koeco_api.utils.UtilsValidate;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CountrySearch {
    private String name;

    public void simpleValidate(){
        UtilsValidate.validateFields(this);
    }
}
