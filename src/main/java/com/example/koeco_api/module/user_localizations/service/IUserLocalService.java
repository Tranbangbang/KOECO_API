package com.example.koeco_api.module.user_localizations.service;

import com.example.koeco_api.module.user_localizations.dto.request.UserLocalRegisRequest;
import com.example.koeco_api.module.user_localizations.dto.request.UserLocalUpdateRequest;
import com.example.koeco_api.module.user_localizations.dto.response.UserLocalResponse;
import com.example.koeco_api.module.user_localizations.entity.UserLocalizationEntity;

public interface IUserLocalService {
    public UserLocalizationEntity findById(Long id);
    public UserLocalResponse register(UserLocalRegisRequest request);
    public UserLocalResponse update(UserLocalUpdateRequest request, Long id);
    public void delete(Long id);
}
