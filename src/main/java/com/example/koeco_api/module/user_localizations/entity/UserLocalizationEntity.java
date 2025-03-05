package com.example.koeco_api.module.user_localizations.entity;

import com.example.koeco_api.enums.LanguageCodeEnum;
import com.example.koeco_api.module.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tbl_user_localization")
public class UserLocalizationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "language_code")
    @Enumerated(EnumType.STRING)
    private LanguageCodeEnum languageCode;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_description")
    private String companyDescription;

    @Column(name = "company_field")
    private String companyField;

    @Column(name = "company_address")
    private String address;

    @Column(name = "director")
    private String director;


    //---mapper entity
    @ManyToOne
    @JoinColumn(name = "user_id")
    @NotNull
    private User user;
}
