package com.example.koeco_api.module.exhibition.repository;

import com.example.koeco_api.module.exhibition.entity.Exhibition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExhibitionRepository extends JpaRepository<Exhibition, Long> {
}
