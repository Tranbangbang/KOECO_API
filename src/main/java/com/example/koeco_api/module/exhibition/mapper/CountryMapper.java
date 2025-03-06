package com.example.koeco_api.module.exhibition.mapper;

import com.example.koeco_api.module.exhibition.dto.request.country.CountryCreate;
import com.example.koeco_api.module.exhibition.dto.response.CountryResponse;
import com.example.koeco_api.module.exhibition.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
public class CountryMapper {
    @Autowired
    private CityMapper cityMapper;

    public Country convertToEntity(CountryCreate request) {
        return Country.builder()
                .nameKr(request.getNameKr())
                .nameEn(request.getNameEn())
                .nameCn(request.getNameCn())
                .build();
    }

    public CountryResponse convertToResponse(Country entity) {
        return CountryResponse.builder()
                .id(entity.getId())
                .nameKr(entity.getNameKr())
                .nameEn(entity.getNameEn())
                .nameCn(entity.getNameCn())
                .cities(entity.getCities() != null ?
                        entity.getCities().stream().map(it -> cityMapper.convertToResponse(it)).toList()
                        : Collections.emptyList()
                )
                .build();
    }
}
