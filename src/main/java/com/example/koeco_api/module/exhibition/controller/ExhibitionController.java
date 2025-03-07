package com.example.koeco_api.module.exhibition.controller;

import com.example.koeco_api.enums.TypeFileEnums;
import com.example.koeco_api.utils.UtilsMedia;
import com.example.koeco_api.utils.UtilsValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;


@RestController
@RequestMapping(UtilsValue.BASE_URL + "/exhibition")

public class ExhibitionController {
    private static final Logger logger = LoggerFactory.getLogger(ExhibitionController.class);
    @PostMapping("/upload_file")
    public String upfile(MultipartFile file){
        return UtilsMedia.uploadFile(file, TypeFileEnums.ALL, 100.0);
    }
}
