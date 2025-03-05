package com.example.koeco_api.module.exhibition.repository;

import com.example.koeco_api.module.exhibition.entity.ExhibitionRegister;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExhibitionRegisterRepository extends JpaRepository<ExhibitionRegister, Long> {
}
