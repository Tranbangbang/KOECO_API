package com.example.koeco_api.module.exhibition.entity;

import com.example.koeco_api.common.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_region")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Region extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kr", nullable = false, length = 255)
    private String nameKr;

    @Column(name = "name_en", length = 255)
    private String nameEn;

    @Column(name = "name_cn", length = 255)
    private String nameCn;
}
