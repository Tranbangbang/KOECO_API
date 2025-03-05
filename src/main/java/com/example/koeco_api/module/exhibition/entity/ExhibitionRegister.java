package com.example.koeco_api.module.exhibition.entity;

import com.example.koeco_api.common.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_exhibition_register")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionRegister extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "exhibition_id", nullable = false)
    private Exhibition exhibition;

    @Column(name = "company_name_kr", nullable = false, length = 255)
    private String companyNameKr;

    @Column(name = "company_name_en", length = 255)
    private String companyNameEn;

    @Column(name = "representative_kr", nullable = false, length = 100)
    private String representativeKr;

    @Column(name = "representative_en", length = 100)
    private String representativeEn;

    @Column(name = "contact_name_kr", nullable = false, length = 100)
    private String contactNameKr;
    @Column(name = "contact_name_en", length = 100)
    private String contactNameEn;

    @Column(name = "contact_phone", length = 20)
    private String contactPhone;

    @Column(name = "contact_email", length = 100)
    private String contactEmail;

    @Column(name = "url", length = 255)
    private String url;

    @Column(name = "address_kr", length = 255)
    private String addressKr;

    @Column(name = "address_en", length = 255)
    private String addressEn;

    @Column(name = "product_name_kr", length = 255)
    private String productNameKr;

    @Column(name = "product_name_en", length = 255)
    private String productNameEn;

    @Column(name = "booth_size", length = 255)
    private String boothSize;

    @Column(name = "business_registration_file_path", length = 255)
    private String businessRegistrationFilePath;

    @Column(name = "corporate_registration_file_path", length = 255)
    private String corporateRegistrationFilePath; // Đường dẫn tệp đăng ký công ty

}
