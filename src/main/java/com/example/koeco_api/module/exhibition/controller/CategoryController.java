package com.example.koeco_api.module.exhibition.controller;

import com.example.koeco_api.common.DefaultRes;
import com.example.koeco_api.module.exhibition.dto.request.category.CategoryCreateRequest;
import com.example.koeco_api.module.exhibition.dto.request.category.CategoryUpdateRequest;
import com.example.koeco_api.module.exhibition.dto.request.city.CityCreateRequest;
import com.example.koeco_api.module.exhibition.dto.request.city.CityUpdateRequest;
import com.example.koeco_api.module.exhibition.dto.response.CategoryResponse;
import com.example.koeco_api.module.exhibition.dto.response.CityResponse;
import com.example.koeco_api.module.exhibition.service.ICategoryService;
import com.example.koeco_api.module.exhibition.service.ICityService;
import com.example.koeco_api.utils.UtilsValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UtilsValue.BASE_URL + "/category")
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;

    @GetMapping("")
    public DefaultRes<List<CategoryResponse>> get(){
        return DefaultRes.<List<CategoryResponse>>builder()
                .statusCode(200)
                .data(categoryService.findAll())
                .build();
    }

    @PostMapping("/create")
    public DefaultRes<CategoryResponse> create(@RequestBody CategoryCreateRequest request){
        return DefaultRes.<CategoryResponse>builder()
                .statusCode(200)
                .data(categoryService.create(request))
                .build();
    }

    @PostMapping("/update/{id}")
    public DefaultRes<CategoryResponse> update(@RequestBody CategoryUpdateRequest request,
                                           @PathVariable Long id){
        return DefaultRes.<CategoryResponse>builder()
                .statusCode(200)
                .data(categoryService.update(request, id))
                .build();
    }

    @PostMapping("/delete{id}")
    public DefaultRes<String> delete(@PathVariable Long id){
        categoryService.delete(id);

        return DefaultRes.<String>builder()
                .statusCode(200)
                .data("delete category")
                .build();
    }
}
