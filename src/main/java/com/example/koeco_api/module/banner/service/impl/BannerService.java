package com.example.koeco_api.module.banner.service.impl;

import com.example.koeco_api.common.CommonException;
import com.example.koeco_api.common.ErrorCode;
import com.example.koeco_api.module.banner.dto.BannerRequest;
import com.example.koeco_api.module.banner.dto.BannerResponse;
import com.example.koeco_api.module.banner.entity.Banner;
import com.example.koeco_api.module.banner.mapper.BannerMapper;
import com.example.koeco_api.module.banner.repository.IBannerRepository;
import com.example.koeco_api.module.banner.service.IBannerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

@Service
public class BannerService implements IBannerService {
    private final IBannerRepository _repository;
    private final BannerMapper _mapper;
    private final String UPLOAD_DIR = "uploads/";

    public BannerService(IBannerRepository repository, BannerMapper mapper) {
        _repository = repository;
        _mapper = mapper;
    }

    @Override
    public BannerResponse createBanner(BannerRequest request) {
        try {
            Banner banner = _mapper.toEntity(request);
            if (request.getImagePath() != null && !request.getImagePath().isEmpty()) {
                String imagePath = saveImage(request.getImagePath());
                banner.setImagePath(imagePath);
            }
            _repository.save(banner);
            return _mapper.toResponse(banner);
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }



    @Override
    public Page<BannerResponse> getBannerList(PageRequest pageRequest) {
        try {
            return _repository.findByIsDeleteFalse(pageRequest)
                    .map(_mapper::toResponse);
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }

    @Override
    public BannerResponse getBannerDetail(Long id) {
        try {
            Banner banner = _repository.findById(id)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
            return _mapper.toResponse(banner);
        } catch (CommonException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }

    @Override
    public void deleteBanner(Long id) {
        try {
            Banner banner = _repository.findById(id)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
            banner.setIsDelete(true);
            _repository.save(banner);
        } catch (CommonException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }


    @Override
    public BannerResponse updateBanner(Long id, BannerRequest request) {
        try {
            Banner banner = _repository.findById(id)
                    .orElseThrow(() -> new CommonException(ErrorCode.NOT_FOUND));
            String imagePath = banner.getImagePath();
            if (request.getImagePath() != null && !request.getImagePath().isEmpty()) {
                imagePath = saveImage(request.getImagePath());
            }
            banner.setTitle(request.getTitle());
            banner.setImagePath(imagePath);
            banner.setLink(request.getLink());
            banner.setPos(request.getPos());
            banner.setDisplayStartDate(LocalDate.parse(request.getDisplayStartDate()));
            banner.setDisplayEndDate(LocalDate.parse(request.getDisplayEndDate()));
            _repository.save(banner);
            return _mapper.toResponse(banner);
        } catch (Exception ex) {
            throw new CommonException(ErrorCode.INTER_SERVER_ERROR);
        }
    }



    private String saveImage(MultipartFile file) {
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
