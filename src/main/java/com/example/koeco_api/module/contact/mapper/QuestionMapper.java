package com.example.koeco_api.module.contact.mapper;

import com.example.koeco_api.module.contact.dto.QuestionRequest;
import com.example.koeco_api.module.contact.dto.QuestionResponse;
import com.example.koeco_api.module.contact.entity.ExhibitionInquiry;
import org.springframework.stereotype.Component;

@Component
public class QuestionMapper {
    public ExhibitionInquiry toEntity(QuestionRequest request) {
        return ExhibitionInquiry.builder()
                .title(request.getTitle())
                .inquirerName(request.getInquirerName())
                .companyName(request.getCompanyName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .address(request.getAddress())
                .content(request.getContent())
                .captcha(request.getCaptcha())
                .build();
    }

    public QuestionResponse toResponse(ExhibitionInquiry entity) {
        QuestionResponse response = new QuestionResponse();
        response.setId(entity.getId());
        response.setTitle(entity.getTitle());
        response.setInquirerName(entity.getInquirerName());
        response.setCompanyName(entity.getCompanyName());
        response.setPhoneNumber(entity.getPhoneNumber());
        response.setEmail(entity.getEmail());
        response.setAddress(entity.getAddress());
        response.setContent(entity.getContent());
        response.setCaptcha(entity.getCaptcha());
        return response;
    }
}
