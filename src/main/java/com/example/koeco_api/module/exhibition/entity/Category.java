package com.example.koeco_api.module.exhibition.entity;

import com.example.koeco_api.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Column(name = "name_kr", nullable = false, length = 255)
    private String nameKr;

    @NotNull
    @Column(name = "name_en", nullable = false, length = 255)
    private String nameEn;

    @NotNull
    @Column(name = "name_cn", nullable = false, length = 255)
    private String nameCn;

    @Column(name = "display_order")
    private Integer displayOrder; // 노출 순서 (Thứ tự hiển thị)

    @ManyToMany(mappedBy = "categories")
    private Set<Exhibition> exhibitions;

}
