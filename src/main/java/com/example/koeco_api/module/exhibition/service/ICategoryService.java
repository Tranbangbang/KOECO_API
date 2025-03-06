package com.example.koeco_api.module.exhibition.service;

import com.example.koeco_api.module.exhibition.dto.request.category.CategoryCreateRequest;
import com.example.koeco_api.module.exhibition.dto.request.category.CategoryUpdateRequest;
import com.example.koeco_api.module.exhibition.dto.response.CategoryResponse;
import com.example.koeco_api.module.exhibition.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICategoryService {
    public Category findById(Long id);
    public List<CategoryResponse> findAll();
    public Page<CategoryResponse> findPageAll(Pageable pageable);
    public CategoryResponse create(CategoryCreateRequest request);
    public CategoryResponse update(CategoryUpdateRequest request, Long id);
    public void delete(Long id);
}
