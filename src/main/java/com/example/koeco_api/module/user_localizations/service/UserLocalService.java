package com.example.koeco_api.module.user_localizations.service;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.module.user_localizations.dto.request.UserLocalRegisRequest;
import com.example.koeco_api.module.user_localizations.dto.request.UserLocalUpdateRequest;
import com.example.koeco_api.module.user_localizations.dto.response.UserLocalResponse;
import com.example.koeco_api.module.user_localizations.entity.UserLocalizationEntity;
import com.example.koeco_api.module.user_localizations.mapper.UserLocalMapper;
import com.example.koeco_api.module.user_localizations.repository.IUserLocalizationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserLocalService implements IUserLocalService {
    @Autowired
    IUserLocalizationRepository userLocalRepository;
    @Autowired
    UserLocalMapper userLocalMapper;

    @Override
    public UserLocalizationEntity findById(Long id) {
        return userLocalRepository.findById(id).orElseThrow(
                ()->  new CommonException(ErrorCode.USER_LOCALIZATION_NOT_FOUND)
        );
    }

    @Override
    public UserLocalResponse register(UserLocalRegisRequest request) {
        var userLocal = userLocalMapper.convertToEntity(request);
        var userSave = userLocalRepository.save(userLocal);
        return userLocalMapper.convertToResponse(userSave);
    }

    @Override
    public UserLocalResponse update(UserLocalUpdateRequest request, Long id) {
        var userLocalUpdate = findById(id);

        userLocalUpdate.setCompanyName(request.getCompanyName());
        userLocalUpdate.setCompanyDescription(request.getCompanyDescription());
        userLocalUpdate.setCompanyField(request.getCompanyField());
        userLocalUpdate.setAddress(request.getAddress());
        userLocalUpdate.setDirector(request.getDirector());

        var userLocal = userLocalRepository.save(userLocalUpdate);
        return userLocalMapper.convertToResponse(userLocal);
    }

    @Override
    public void delete(Long id) {
        if(userLocalRepository.existsById(id))
            userLocalRepository.deleteById(id);
        else
            throw new CommonException(ErrorCode.USER_LOCALIZATION_NOT_FOUND);
    }
}
