package com.example.koeco_api.module.exhibition.entity;

import com.example.koeco_api.common.Auditable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_city")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class City extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name_kr", nullable = false, length = 255)
    private String nameKr;

    @NotNull
    @Column(name = "name_en", length = 255)
    private String nameEn;

    @NotNull
    @Column(name = "name_cn", length = 255)
    private String nameCn;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;
}
