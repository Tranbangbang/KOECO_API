package com.example.koeco_api.module.exhibition.entity;

import com.example.koeco_api.common.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "tbl_category")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kr", nullable = false, length = 255)
    private String nameKr;

    @Column(name = "name_en", length = 255)
    private String nameEn;

    @Column(name = "name_cn", length = 255)
    private String nameCn;

    @Column(name = "display_order")
    private Integer displayOrder; // 노출 순서 (Thứ tự hiển thị)

    @ManyToMany(mappedBy = "categories")
    private Set<Exhibition> exhibitions;

}
