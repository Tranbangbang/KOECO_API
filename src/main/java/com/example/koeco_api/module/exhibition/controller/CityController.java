package com.example.koeco_api.module.exhibition.controller;

import com.example.koeco_api.common.DefaultRes;
import com.example.koeco_api.module.exhibition.dto.request.city.CityCreateRequest;
import com.example.koeco_api.module.exhibition.dto.request.city.CityUpdateRequest;
import com.example.koeco_api.module.exhibition.dto.response.CityResponse;
import com.example.koeco_api.module.exhibition.service.ICityService;
import com.example.koeco_api.utils.UtilsValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UtilsValue.BASE_URL + "/city")
public class CityController {
    @Autowired
    private ICityService cityService;

    @GetMapping("")
    public DefaultRes<List<CityResponse>> get(){
        return DefaultRes.<List<CityResponse>>builder()
                .statusCode(200)
                .data(cityService.findAll())
                .build();
    }

    @PostMapping("/create")
    public DefaultRes<CityResponse> create(@RequestBody CityCreateRequest request){
        return DefaultRes.<CityResponse>builder()
                .statusCode(200)
                .data(cityService.create(request))
                .build();
    }

    @PostMapping("/update/{id}")
    public DefaultRes<CityResponse> update(@RequestBody CityUpdateRequest request,
                                           @PathVariable Long id){
        return DefaultRes.<CityResponse>builder()
                .statusCode(200)
                .data(cityService.update(request, id))
                .build();
    }

    @PostMapping("/delete{id}")
    public DefaultRes<String> delete(@PathVariable Long id){
        cityService.delete(id);

        return DefaultRes.<String>builder()
                .statusCode(200)
                .data("delete city")
                .build();
    }
}
