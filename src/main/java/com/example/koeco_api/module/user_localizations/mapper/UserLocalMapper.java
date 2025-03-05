package com.example.koeco_api.module.user_localizations.mapper;

import com.example.koeco_api.module.user.entity.User;
import com.example.koeco_api.module.user.service.IUserCustomService;
import com.example.koeco_api.module.user_localizations.dto.request.UserLocalRegisRequest;
import com.example.koeco_api.module.user_localizations.dto.response.UserLocalResponse;
import com.example.koeco_api.module.user_localizations.entity.UserLocalizationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserLocalMapper {
    @Autowired
    IUserCustomService userCustomService;

    public UserLocalizationEntity convertToEntity(UserLocalRegisRequest request){
        User user = userCustomService.findByUserid(request.getUserId());

        return UserLocalizationEntity.builder()
                .languageCode(request.getLanguageCode())
                .companyName(request.getCompanyName())
                .companyDescription(request.getCompanyDescription())
                .companyField(request.getCompanyField())
                .address(request.getAddress())
                .director(request.getDirector())
                .user(user)
                .build();
    }

    public UserLocalResponse convertToResponse(UserLocalizationEntity entity){
        return UserLocalResponse.builder()
                .id(entity.getId())
                .languageCode(entity.getLanguageCode())
                .companyName(entity.getCompanyName())
                .companyDescription(entity.getCompanyDescription())
                .companyField(entity.getCompanyField())
                .address(entity.getAddress())
                .director(entity.getDirector())
                .userId(entity.getUser().getUserId())
                .build();
    }
}
