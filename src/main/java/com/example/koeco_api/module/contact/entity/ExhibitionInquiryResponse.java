package com.example.koeco_api.module.contact.entity;

import com.example.koeco_api.common.Auditable;
import com.example.koeco_api.module.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_exhibition_inquiry_response")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExhibitionInquiryResponse extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "inquiry_id", nullable = false)
    private ExhibitionInquiry inquiry; // 문의사항 ID (Liên kết với câu hỏi)

    @Column(name = "admin_name", nullable = false, length = 100)
    private String adminName; // Người trả lời (Admin)

    @Column(name = "response_content", columnDefinition = "TEXT", nullable = false)
    private String responseContent; // Nội dung phản hồi

    @ManyToOne
    @JoinColumn(name = "admin_id", nullable = false)
    private User admin; // Người phản hồi câu hỏi (Admin)

}
