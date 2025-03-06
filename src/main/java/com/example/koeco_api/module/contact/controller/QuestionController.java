package com.example.koeco_api.module.contact.controller;

import com.example.koeco_api.common.DefaultRes;
import com.example.koeco_api.common.StatusCode;
import com.example.koeco_api.module.contact.dto.QuestionRequest;
import com.example.koeco_api.module.contact.dto.QuestionResponse;
import com.example.koeco_api.module.contact.service.IQuestionService;
import com.example.koeco_api.module.contact.service.impl.CaptchaService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Tag(name = "07.Question")
@RestController
@RequestMapping("/api/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final IQuestionService questionService;
    private final CaptchaService captchaService;

    @PostMapping("/create")
    public ResponseEntity<DefaultRes<QuestionResponse>> createQuestion(
            @Validated @RequestBody QuestionRequest request) {
        QuestionResponse response = questionService.createQuestion(request);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.CREATED, "Thêm câu hỏi viên thành công", response));
    }

    @GetMapping("/list")
    public ResponseEntity<DefaultRes<Page<QuestionResponse>>> getAllQuestions(
            int page, int size) {
        Page<QuestionResponse> questions = questionService.getAllQuestions(page, size);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Lấy danh sách câu hỏi thành công", questions));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DefaultRes<QuestionResponse>> getQuestionById(@PathVariable Long id) {
        QuestionResponse response = questionService.getQuestionById(id);
        return ResponseEntity.ok(DefaultRes.res(StatusCode.OK, "Lấy chi tiết câu hỏi thành công", response));
    }

}
