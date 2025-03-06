package com.example.koeco_api.module.exhibition.service.impl;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.module.exhibition.dto.request.country.CountryCreate;
import com.example.koeco_api.module.exhibition.dto.response.CountryResponse;
import com.example.koeco_api.module.exhibition.entity.Country;
import com.example.koeco_api.module.exhibition.mapper.CountryMapper;
import com.example.koeco_api.module.exhibition.repository.ICountryRepository;
import com.example.koeco_api.module.exhibition.service.ICountryService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CountryService implements ICountryService {
    @Autowired
    private ICountryRepository countryRepository;
    @Autowired
    private CountryMapper countryMapper;

    @Override
    public Country findById(Long id) {
        return countryRepository.findById(id).orElseThrow(
                () -> new CommonException(ErrorCode.COUNTRY_NOT_FOUND)
        );
    }

    @Override
    public List<CountryResponse> findAll() {
        return countryRepository.findAll().stream().map(countryMapper::convertToResponse).toList();
    }

    @Override
    public Page<CountryResponse> findPageAll(Pageable pageable) {
        return countryRepository.findAll(pageable).map(countryMapper::convertToResponse);
    }

    @Override
    public CountryResponse create(CountryCreate request) {
        if(request.getNameKr() != null && countryRepository.existsByNameKr(request.getNameKr()))
            throw new CommonException(ErrorCode.COUNTRY_EXITS);
        if(request.getNameEn() != null && countryRepository.existsByNameEn(request.getNameEn()))
            throw new CommonException(ErrorCode.COUNTRY_EXITS);
        if(request.getNameCn() != null && countryRepository.existsByNameCn(request.getNameCn()))
            throw new CommonException(ErrorCode.COUNTRY_EXITS);

        var entity = countryMapper.convertToEntity(request);
        return countryMapper.convertToResponse(countryRepository.save(entity));
    }
}
