package com.example.koeco_api.module.blacklist_token.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_blacklist_token")
public class BlacklistTokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "jwt_id")
    private String jwtId;
    private Long expired;
}
