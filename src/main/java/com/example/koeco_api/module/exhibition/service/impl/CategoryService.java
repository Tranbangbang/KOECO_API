package com.example.koeco_api.module.exhibition.service.impl;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.module.exhibition.dto.request.category.CategoryCreateRequest;
import com.example.koeco_api.module.exhibition.dto.request.category.CategoryUpdateRequest;
import com.example.koeco_api.module.exhibition.dto.response.CategoryResponse;
import com.example.koeco_api.module.exhibition.entity.Category;
import com.example.koeco_api.module.exhibition.mapper.CategoryMapper;
import com.example.koeco_api.module.exhibition.repository.ICategoryRepository;
import com.example.koeco_api.module.exhibition.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElseThrow(
                () -> new CommonException(ErrorCode.COUNTRY_EXITS)
        );
    }

    @Override
    public List<CategoryResponse> findAll() {
        return categoryRepository.findAll().stream()
                .map(categoryMapper::convertToResponse).toList();
    }

    @Override
    public Page<CategoryResponse> findPageAll(Pageable pageable) {
        return categoryRepository.findAll(pageable)
                .map(categoryMapper::convertToResponse);
    }

    @Override
    public CategoryResponse create(CategoryCreateRequest request) {
        var entity = categoryMapper.convertToEntity(request);

        if(this.checkExitsName(request.getNameKr(), request.getNameEn(), request.getNameCn()))
            throw new CommonException(ErrorCode.CATEGORY_EXITS);

        return categoryMapper.convertToResponse(categoryRepository.save(entity));
    }

    @Override
    public CategoryResponse update(CategoryUpdateRequest request, Long id) {
        if(this.checkExitsNameUpdate(request.getNameKr(), request.getNameEn(), request.getNameCn(), id))
            throw new CommonException(ErrorCode.CATEGORY_EXITS);

        var entity = this.findById(id);
        entity.setNameKr(request.getNameKr());
        entity.setNameEn(request.getNameEn());
        entity.setNameCn(request.getNameCn());
        entity.setDisplayOrder(request.getDisplayOrder());

        return categoryMapper.convertToResponse(categoryRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        var entity = this.findById(id);
        categoryRepository.deleteById(id);
    }

    private boolean checkExitsName(String cateKr, String cateEn, String cate_ch){
        if(categoryRepository.existsByNameKr(cateKr))
            return true;
        if(categoryRepository.existsByNameEn(cateEn))
            return true;
        if(categoryRepository.existsByNameCn(cate_ch))
            return true;

        return false;
    }

    private boolean checkExitsNameUpdate(String cateKr, String cateEn, String cate_ch, Long id ){
        if(categoryRepository.existsByNameKrUpdate(cateKr, id))
            return true;
        if(categoryRepository.existsByNameEnUpdate(cateEn, id))
            return true;
        if(categoryRepository.existsByNameCnUpdate(cate_ch, id))
            return true;
        return false;
    }
}
