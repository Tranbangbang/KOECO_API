package com.example.koeco_api.module.exhibition.service;

import com.example.koeco_api.module.exhibition.dto.request.ExhibitionRegisterRequest;
import com.example.koeco_api.module.exhibition.dto.response.ExhibitionRegisterResponse;

public interface IExhibitionRegisterService {
    ExhibitionRegisterResponse createExhibitionRegister(ExhibitionRegisterRequest request);
    ExhibitionRegisterResponse getExhibitionRegister(Long id);
}
