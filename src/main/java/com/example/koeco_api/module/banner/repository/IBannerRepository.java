package com.example.koeco_api.module.banner.repository;

import com.example.koeco_api.module.banner.entity.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBannerRepository extends JpaRepository<Banner, Long> {

    Page<Banner> findByIsDeleteFalse(PageRequest pageRequest);
}
