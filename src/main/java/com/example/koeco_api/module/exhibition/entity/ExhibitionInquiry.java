package com.example.koeco_api.module.exhibition.entity;

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
    private String title; // 제목 (Tiêu đề)

    @Column(name = "inquirer_name", nullable = false, length = 100)
    private String inquirerName; // 이름 (Tên người hỏi)

    @Column(name = "company_name", length = 255)
    private String companyName; // 업체명 (Tên công ty)

    @Column(name = "phone_number", length = 20)
    private String phoneNumber; // 전화번호 (Số điện thoại)

    @Column(name = "email", length = 100)
    private String email; // 이메일 (Email liên hệ)

    @Column(name = "address", length = 255)
    private String address; // 주소 (Địa chỉ)

    @Column(name = "content", columnDefinition = "TEXT", nullable = false)
    private String content; // Nội dung câu hỏi

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
