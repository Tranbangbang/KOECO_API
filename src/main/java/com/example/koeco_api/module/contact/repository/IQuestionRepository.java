package com.example.koeco_api.module.contact.repository;

import com.example.koeco_api.module.contact.entity.ExhibitionInquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IQuestionRepository extends JpaRepository<ExhibitionInquiry, Long> {

}
