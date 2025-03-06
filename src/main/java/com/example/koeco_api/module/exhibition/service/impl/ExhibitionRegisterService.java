package com.example.koeco_api.module.exhibition.service.impl;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.module.exhibition.dto.request.ExhibitionRegisterRequest;
import com.example.koeco_api.module.exhibition.dto.request.ExhibitionRegisterUpdateRequest;
import com.example.koeco_api.module.exhibition.dto.response.ExhibitionRegisterResponse;
import com.example.koeco_api.module.exhibition.entity.Exhibition;
import com.example.koeco_api.module.exhibition.entity.ExhibitionRegister;
import com.example.koeco_api.module.exhibition.mapper.ExhibitionRegisterMapper;
import com.example.koeco_api.module.exhibition.repository.IExhibitionRegisterRepository;
import com.example.koeco_api.module.exhibition.repository.IExhibitionRepository;
import com.example.koeco_api.module.exhibition.service.IExhibitionRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private IExhibitionRepository exhibitionRepository;

    @Autowired
    private ExhibitionRegisterMapper exhibitionRegisterMapper;

    private final String UPLOAD_DIR = "uploads/";

    @Override
    public ExhibitionRegisterResponse createExhibitionRegister(ExhibitionRegisterRequest request) {
        try {
            String businessFilePath = saveFile(request.getBusinessRegistrationFile());
            String corporateFilePath = saveFile(request.getCorporateRegistrationFile());

            Exhibition exhibition = exhibitionRepository.findById(request.getExhibitionId())
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
            ExhibitionRegister exhibitionRegister = exhibitionRegisterMapper.toEntity(request, exhibition);
            exhibitionRegister.setBusinessRegistrationFilePath(businessFilePath);
            exhibitionRegister.setCorporateRegistrationFilePath(corporateFilePath);

            ExhibitionRegister savedExhibitionRegister = exhibitionRegisterRepository.save(exhibitionRegister);
            return exhibitionRegisterMapper.toResponse(savedExhibitionRegister);
        } catch (CommonException ex) {
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

    @Override
    public Page<ExhibitionRegisterResponse> getAllExhibitionRegisters(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ExhibitionRegister> registers = exhibitionRegisterRepository.findAll(pageable);
        return registers.map(register -> ExhibitionRegisterResponse.builder()
                .id(register.getId())
                .companyNameKr(register.getCompanyNameKr())
                .productNameKr(register.getProductNameKr())
                .exhibitionName(register.getExhibition().getNameKr())
                .createDate(register.getCreate_Date())
                .build());
    }

    @Override
    public ExhibitionRegisterResponse updateExhibitionRegister(Long id, ExhibitionRegisterUpdateRequest updateRequest) {
        try {
            ExhibitionRegister exhibitionRegister = exhibitionRegisterRepository.findById(id)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
            exhibitionRegister.setApplicationScale(updateRequest.getApplicationScale());
            exhibitionRegister.setBoothSize(updateRequest.getBoothSize());
            ExhibitionRegister updatedRegister = exhibitionRegisterRepository.save(exhibitionRegister);
            return exhibitionRegisterMapper.toResponse(updatedRegister);
        } catch (CommonException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }
}
