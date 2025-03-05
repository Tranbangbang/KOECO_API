package com.example.koeco_api.module.boardpost.entity;
import com.example.koeco_api.common.Auditable;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Table(name = "tbl_board_category")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardCategory extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_kr", nullable = false, length = 255)
    private String nameCategory;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}
