package com.example.koeco_api.module.exhibition.service;

import com.example.koeco_api.module.exhibition.dto.request.city.CityCreateRequest;
import com.example.koeco_api.module.exhibition.dto.request.city.CityUpdateRequest;
import com.example.koeco_api.module.exhibition.dto.response.CityResponse;
import com.example.koeco_api.module.exhibition.entity.City;

import java.util.List;

public interface ICityService {
    public City findById(Long id);
    public List<CityResponse> findAll();
    public boolean exitsByCountryAndCity(String country_kr, String city_kr, String city_en, String city_cn);
    public CityResponse create(CityCreateRequest request);
    public CityResponse update(CityUpdateRequest request, Long id);
    public void delete (Long id);
}
