package com.example.koeco_api.module.banner.entity;


import com.example.koeco_api.common.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "tbl_banner")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Banner extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false, length = 255)
    private String title; // 제목 (Tiêu đề slide banner)

    @Column(name = "image_path", nullable = false, length = 255)
    private String imagePath; // 이미지 (Đường dẫn ảnh)

    @Column(name = "link", length = 255)
    private String link; // 링크 (Liên kết)

    private int pos; // thứ tự hiển thị

    @Column(name = "display_start_date")
    private LocalDate displayStartDate; // 노출기간 시작 (Ngày bắt đầu hiển thị)

    @Column(name = "display_end_date")
    private LocalDate displayEndDate; // 노출기간 종료 (Ngày kết thúc hiển thị)
}
