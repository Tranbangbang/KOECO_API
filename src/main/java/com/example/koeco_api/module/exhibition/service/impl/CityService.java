package com.example.koeco_api.module.exhibition.service.impl;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.module.exhibition.dto.request.city.CityCreateRequest;
import com.example.koeco_api.module.exhibition.dto.request.city.CityUpdateRequest;
import com.example.koeco_api.module.exhibition.dto.response.CityResponse;
import com.example.koeco_api.module.exhibition.entity.City;
import com.example.koeco_api.module.exhibition.mapper.CityMapper;
import com.example.koeco_api.module.exhibition.repository.ICityRepository;
import com.example.koeco_api.module.exhibition.service.ICityService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CityService implements ICityService {
    @Autowired
    private ICityRepository cityRepository;
    @Autowired
    private CityMapper cityMapper;

    @Override
    public City findById(Long id) {
        return cityRepository.findById(id).orElseThrow(
                ()-> new CommonException(ErrorCode.CITY_NOT_FOUND)
        );
    }

    @Override
    public List<CityResponse> findAll() {
        return cityRepository.findAll().stream()
                .map(cityMapper::convertToResponse).toList();
    }

    @Override
    public boolean exitsByCountryAndCity(String country_kr, String city_kr, String city_en, String city_cn) {
        if(cityRepository.existsByCountryAndNameKr(country_kr, city_kr))
            return true;

        if(cityRepository.existsByCountryAndNameEn(country_kr, city_en))
            return  true;

        if(cityRepository.existsByCountryAndNameCn(country_kr, city_cn))
            return true;

        return false;
    }

    @Override
    public CityResponse create(CityCreateRequest request) {
        var entity = cityMapper.convertToEntity(request);

        if(this.exitsByCountryAndCity(entity.getCountry().getNameKr(),
                request.getNameKr(), request.getNameEn(), request.getNameCn()))
            throw new CommonException(ErrorCode.CITY_EXITS);

        return cityMapper.convertToResponse(cityRepository.save(entity));
    }

    @Override
    public CityResponse update(CityUpdateRequest request, Long id) {
        var entity = this.findById(id);

        entity.setNameKr(request.getNameKr());
        entity.setNameEn(request.getNameEn());
        entity.setNameCn(request.getNameCn());

        return cityMapper.convertToResponse(cityRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        cityRepository.deleteById(id);
    }
}
