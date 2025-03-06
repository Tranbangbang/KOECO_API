package com.example.koeco_api.module.exhibition.entity;


import com.example.koeco_api.common.ApplicationStatus;
import com.example.koeco_api.common.Auditable;
import com.example.koeco_api.module.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_exhibition")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Exhibition extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kr", nullable = false, length = 255)
    private String nameKr;

    @Column(name = "name_en", nullable = false, length = 255)
    private String nameEn;

    @Column(name = "name_cn", nullable = false, length = 255)
    private String nameCn;

    @Column(name = "is_highlighted")
    private Boolean isHighlighted; // 노출유무 (Hiển thị hay không)

    @ManyToOne
    @JoinColumn(name = "region_id")
    private Region region; // 지역 (Khu vực triển lãm)

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city; // 도시 (Thành phố)

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "venue", length = 255)
    private String venue; // 전시장 (Địa điểm triển lãm)

    @Column(name = "website", length = 255)
    private String website;

    @Column(name = "video_url", length = 255)
    private String videoUrl;

    @Column(name = "booth_fee", length = 255)
    private String boothFee; // 부스임차료

    @Column(name = "description", columnDefinition = "TEXT")
    private String description; // 행사내용 (Mô tả sự kiện)

    @Column(name = "image_regis")
    private String imageRegis; // 이미지등록 (Ảnh triển lãm)

    @Column(name = "image_large")
    private String imageLarge;

    @Column(name = "pdf_path")
    private String pdfPath; // 파일등록 (Tệp đính kèm)

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ApplicationStatus status; // Trạng thái đăng ký

    @OneToMany(mappedBy = "exhibition")
    private List<ExhibitionDocument> exhibitionDocuments;

    @ManyToMany
    @JoinTable(
            name = "tbl_exhibition_category",
            joinColumns = @JoinColumn(name = "exhibition_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private Set<Category> categories; // 전시분야 (Lĩnh vực triển lãm)

    @ManyToMany
    @JoinTable(
            name = "tbl_exhibition_user",
            joinColumns = @JoinColumn(name = "exhibition_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users;

}
