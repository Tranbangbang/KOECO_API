package com.example.koeco_api.module.partner.entity;

import com.example.koeco_api.common.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_member_company")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberCompany extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "representative", nullable = false)
    private String representative; // Người đại diện

    @Column(name = "company_name", nullable = false, unique = true)
    private String companyName; // Tên công ty

    @Column(name = "phone")
    private String phone; // Số điện thoại

    @Column(name = "main_product")
    private String mainProduct; // Sản phẩm chính

    @Column(name = "address", columnDefinition = "TEXT")
    private String address; // Địa chỉ công ty

    @Column(name = "website")
    private String website; // Website công ty

    @Column(name = "logo_url")
    private String logoUrl;
}
