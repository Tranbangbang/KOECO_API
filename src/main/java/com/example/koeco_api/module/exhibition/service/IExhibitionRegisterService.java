package com.example.koeco_api.module.exhibition.service;

import com.example.koeco_api.module.exhibition.dto.request.ExhibitionRegisterRequest;
import com.example.koeco_api.module.exhibition.dto.request.ExhibitionRegisterUpdateRequest;
import com.example.koeco_api.module.exhibition.dto.response.ExhibitionRegisterResponse;
import org.springframework.data.domain.Page;

public interface IExhibitionRegisterService {
    ExhibitionRegisterResponse createExhibitionRegister(ExhibitionRegisterRequest request);
    ExhibitionRegisterResponse getExhibitionRegister(Long id);
    Page<ExhibitionRegisterResponse> getAllExhibitionRegisters(int page, int size);
    ExhibitionRegisterResponse updateExhibitionRegister(Long id, ExhibitionRegisterUpdateRequest updateRequest);
}
