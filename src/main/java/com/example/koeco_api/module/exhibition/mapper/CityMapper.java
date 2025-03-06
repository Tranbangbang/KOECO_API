package com.example.koeco_api.module.exhibition.mapper;

import com.example.koeco_api.module.exhibition.dto.request.city.CityCreateRequest;
import com.example.koeco_api.module.exhibition.dto.response.CityResponse;
import com.example.koeco_api.module.exhibition.entity.City;
import com.example.koeco_api.module.exhibition.service.ICountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityMapper {
    @Autowired
    private ICountryService countryService;

    public City convertToEntity(CityCreateRequest request){
        var country = countryService.findById(request.getIdCountry());
        return City.builder()
                .nameKr(request.getNameKr())
                .nameEn(request.getNameEn())
                .nameCn(request.getNameCn())
                .country(country)
                .build();
    }

    public CityResponse convertToResponse(City entity){
        return CityResponse.builder()
                .id(entity.getId())
                .nameKr(entity.getNameKr())
                .nameEn(entity.getNameEn())
                .nameCn(entity.getNameCn())
                .build();
    }
}
