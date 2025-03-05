package com.example.koeco_api.module.partner.repository;

import com.example.koeco_api.module.partner.entity.MemberCompany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IMemberCompanyRepository extends JpaRepository<MemberCompany, Long> {
    boolean existsByCompanyName(String companyName);

    @Query("SELECT m FROM MemberCompany m WHERE m.isDelete = false")
    Page<MemberCompany> findByIsDeleteFalse(PageRequest pageRequest);

}
