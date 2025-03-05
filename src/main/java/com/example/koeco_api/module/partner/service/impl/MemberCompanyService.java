package com.example.koeco_api.module.partner.service.impl;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.module.partner.dto.request.MemberCompanyRequest;
import com.example.koeco_api.module.partner.dto.response.MemberCompanyResponse;
import com.example.koeco_api.module.partner.entity.MemberCompany;
import com.example.koeco_api.module.partner.mapper.MemberCompanyMapper;
import com.example.koeco_api.module.partner.repository.IMemberCompanyRepository;
import com.example.koeco_api.module.partner.service.IMemberCompanyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;


@Service
public class MemberCompanyService implements IMemberCompanyService {
    private final IMemberCompanyRepository _repository;
    private final MemberCompanyMapper _mapper;

    private final String UPLOAD_DIR = "uploads/";

    public MemberCompanyService(IMemberCompanyRepository repository, MemberCompanyMapper mapper) {
        _repository = repository;
        _mapper = mapper;
    }

    @Override
    public MemberCompanyResponse createMemberCompany(MemberCompanyRequest request) {
        try {
            if (_repository.existsByCompanyName(request.getCompanyName())) {
                throw new CommonException(ErrorCode.ID_DUPLICATION);
            }
            String logoUrl = null;
            if (request.getLogo() != null && !request.getLogo().isEmpty()) {
                logoUrl = saveLogo(request.getLogo());
            }
            MemberCompany company = _mapper.toEntity(request, logoUrl);
            _repository.save(company);
            return _mapper.toResponse(company);
        } catch (CommonException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }

    @Override
    public Page<MemberCompanyResponse> getMemberCompanies(PageRequest pageRequest) {
        try {
            return _repository.findByIsDeleteFalse(pageRequest)
                    .map(_mapper::toResponse);
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }

    @Override
    public MemberCompanyResponse getMemberCompanyDetail(Long id) {
        try {
            MemberCompany company = _repository.findById(id)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
            company.setViewCount(company.getViewCount() + 1);
            _repository.save(company);
            return _mapper.toResponse(company);
        } catch (CommonException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }

    @Override
    public MemberCompanyResponse updateMemberCompany(Long id, MemberCompanyRequest request) {
        try {
            MemberCompany company = _repository.findById(id)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
            String logoUrl = company.getLogoUrl();
            if (request.getLogo() != null && !request.getLogo().isEmpty()) {
                logoUrl = saveLogo(request.getLogo());
            }
            company.setRepresentative(request.getRepresentative());
            company.setCompanyName(request.getCompanyName());
            company.setPhone(request.getPhone());
            company.setMainProduct(request.getMainProduct());
            company.setAddress(request.getAddress());
            company.setWebsite(request.getWebsite());
            company.setLogoUrl(logoUrl);
            _repository.save(company);
            return _mapper.toResponse(company);
        } catch (CommonException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }

    @Override
    public void deleteMemberCompany(Long id) {
        try {
            MemberCompany company = _repository.findById(id)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
            company.setIsDelete(true);
            company.setModifier_Date(LocalDateTime.now());
            _repository.save(company);
        } catch (CommonException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }

    @Override
    public Page<MemberCompanyResponse> searchMemberCompanies(String companyName, PageRequest pageRequest) {
        try {
            Page<MemberCompany> result = _repository.findByCompanyNameContainingIgnoreCaseAndIsDeleteFalse(companyName, pageRequest);
            if (result == null || result.getSize() == 0) {
                throw new CommonException(ErrorCode.NOT_FOUND);
            }

            return result.map(_mapper::toResponse);
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }



    private String saveLogo(MultipartFile file) {
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
}
