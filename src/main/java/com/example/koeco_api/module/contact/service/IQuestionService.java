package com.example.koeco_api.module.contact.service;

import com.example.koeco_api.module.contact.dto.QuestionRequest;
import com.example.koeco_api.module.contact.dto.QuestionResponse;
import org.springframework.data.domain.Page;

public interface IQuestionService {
    QuestionResponse createQuestion(QuestionRequest request);
    Page<QuestionResponse> getAllQuestions(int page, int size);
    QuestionResponse getQuestionById(Long id);
}
