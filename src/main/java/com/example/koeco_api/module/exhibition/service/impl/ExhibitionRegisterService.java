package com.example.koeco_api.module.exhibition.service.impl;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.module.exhibition.dto.request.ExhibitionRegisterRequest;
import com.example.koeco_api.module.exhibition.dto.response.ExhibitionRegisterResponse;
import com.example.koeco_api.module.exhibition.entity.ExhibitionRegister;
import com.example.koeco_api.module.exhibition.mapper.ExhibitionRegisterMapper;
import com.example.koeco_api.module.exhibition.repository.IExhibitionRegisterRepository;
import com.example.koeco_api.module.exhibition.service.IExhibitionRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ExhibitionRegisterService implements IExhibitionRegisterService {
    @Autowired
    private IExhibitionRegisterRepository exhibitionRegisterRepository;

    @Autowired
    private ExhibitionRegisterMapper exhibitionRegisterMapper;

    private final String UPLOAD_DIR = "uploads/";

    @Override
    public ExhibitionRegisterResponse createExhibitionRegister(ExhibitionRegisterRequest request) {
        try{
            String businessFilePath = saveFile(request.getBusinessRegistrationFile());
            String corporateFilePath = saveFile(request.getCorporateRegistrationFile());
            ExhibitionRegister exhibitionRegister = exhibitionRegisterMapper.toEntity(request);
            exhibitionRegister.setBusinessRegistrationFilePath(businessFilePath);
            exhibitionRegister.setCorporateRegistrationFilePath(corporateFilePath);
            ExhibitionRegister savedExhibitionRegister = exhibitionRegisterRepository.save(exhibitionRegister);
            return exhibitionRegisterMapper.toResponse(savedExhibitionRegister);
        }catch (CommonException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }

    }

    private String saveFile(MultipartFile file) {
        try {
            Path uploadPath = Paths.get(UPLOAD_DIR);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), filePath);

            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/uploads/")
                    .path(fileName)
                    .toUriString();

        } catch (IOException e) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }

    @Override
    public ExhibitionRegisterResponse getExhibitionRegister(Long id) {
        try{
            ExhibitionRegister exhibitionRegister = exhibitionRegisterRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Exhibition register not found"));
            return exhibitionRegisterMapper.toResponse(exhibitionRegister);
        }catch (CommonException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }

    }
}
