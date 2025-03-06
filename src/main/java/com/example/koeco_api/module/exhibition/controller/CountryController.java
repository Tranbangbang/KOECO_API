package com.example.koeco_api.module.exhibition.controller;

import com.example.koeco_api.common.DefaultRes;
import com.example.koeco_api.module.exhibition.dto.request.country.CountryCreate;
import com.example.koeco_api.module.exhibition.dto.response.CountryResponse;
import com.example.koeco_api.module.exhibition.service.ICountryService;
import com.example.koeco_api.utils.UtilsValue;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UtilsValue.BASE_URL + "/country")
public class CountryController {
    @Autowired
    private ICountryService countryService;

    @GetMapping("")
    public DefaultRes<List<CountryResponse>> get(){
        return DefaultRes.<List<CountryResponse>>builder()
                .statusCode(200)
                .data(countryService.findAll())
                .build();
    }

    @GetMapping("/find_all")
    @Operation(description = "pagination country")
    public DefaultRes<Page<CountryResponse>> findAll(@RequestParam(name = "page", defaultValue = "1") Integer page,
                                                     @RequestParam(name = "limit", defaultValue = "10") Integer limit){
        Pageable pageable = PageRequest.of(page-1, limit);


        return DefaultRes.<Page<CountryResponse>>builder()
                .statusCode(200)
                .data(countryService.findPageAll(pageable))
                .build();
    }

    @PostMapping("/create")
    public DefaultRes<CountryResponse> create(@RequestBody CountryCreate request){
        return DefaultRes.<CountryResponse>builder()
                .statusCode(200)
                .data(countryService.create(request))
                .build();
    }
}
