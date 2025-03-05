package com.example.koeco_api.module.user_localizations.repository;

import com.example.koeco_api.module.user_localizations.entity.UserLocalizationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserLocalizationRepository extends JpaRepository<UserLocalizationEntity, Long> {
}
