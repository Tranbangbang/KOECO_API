package com.example.koeco_api.module.contact.entity;

import com.example.koeco_api.common.Auditable;
import com.example.koeco_api.module.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_exhibition_inquiry")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionInquiry extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title; // Tiêu đề câu hỏi

    @Column(name = "inquirer_name", nullable = false, length = 100)
    private String inquirerName; // Tên người hỏi

    @Column(name = "company_name", length = 255)
    private String companyName; // Tên công ty

    @Column(name = "phone_number", length = 20)
    private String phoneNumber; // Số điện thoại

    @Column(name = "email", length = 100)
    private String email; // Email liên hệ

    @Column(name = "address", length = 255)
    private String address; // Địa chỉ

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content; // Nội dung câu hỏi

    @Column(name = "captcha", length = 10)
    private String captcha; // Mã CAPTCHA


}
