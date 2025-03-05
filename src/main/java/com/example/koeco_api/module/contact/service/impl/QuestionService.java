package com.example.koeco_api.module.contact.service.impl;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.module.contact.dto.QuestionRequest;
import com.example.koeco_api.module.contact.dto.QuestionResponse;
import com.example.koeco_api.module.contact.entity.ExhibitionInquiry;
import com.example.koeco_api.module.contact.mapper.QuestionMapper;
import com.example.koeco_api.module.contact.repository.IQuestionRepository;
import com.example.koeco_api.module.contact.service.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class QuestionService implements IQuestionService {
    @Autowired
    IQuestionRepository questionRepository;
    @Autowired
    QuestionMapper questionMapper;

    @Override
    public QuestionResponse createQuestion(QuestionRequest request) {
        try {
            ExhibitionInquiry inquiry = questionMapper.toEntity(request);
            questionRepository.save(inquiry);
            return questionMapper.toResponse(inquiry);
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }

    @Override
    public Page<QuestionResponse> getAllQuestions(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ExhibitionInquiry> inquiries = questionRepository.findAll(pageable);
        return inquiries.map(questionMapper::toResponse);
    }

    @Override
    public QuestionResponse getQuestionById(Long id) {
        try{
            ExhibitionInquiry inquiry = questionRepository.findById(id)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
            return questionMapper.toResponse(inquiry);
        }  catch (CommonException ex) {
            throw ex;
        }catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }

    }
}
