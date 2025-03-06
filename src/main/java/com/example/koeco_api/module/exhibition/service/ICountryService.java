package com.example.koeco_api.module.exhibition.service;


import com.example.koeco_api.module.exhibition.dto.request.country.CountryCreate;
import com.example.koeco_api.module.exhibition.dto.response.CountryResponse;
import com.example.koeco_api.module.exhibition.entity.Country;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICountryService {
    public Country findById(Long id);
    public List<CountryResponse> findAll();
    public Page<CountryResponse> findPageAll(Pageable pageable);
    public CountryResponse create(CountryCreate request);
}
