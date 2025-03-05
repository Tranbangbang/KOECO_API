package com.example.koeco_api.module.user.entity;


import com.example.koeco_api.module.exhibition.entity.Exhibition;
import com.example.koeco_api.module.contact.entity.ExhibitionInquiry;
import com.example.koeco_api.module.contact.entity.ExhibitionInquiryResponse;
import com.example.koeco_api.module.user.common.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tbl_user")
public class User implements UserDetails {
    @Id
    private String userId;
    private String lastName;
    private String firstName;

    @Column(name = "alias", length = 50)
    private String alias;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;


    @ManyToMany(mappedBy = "users")
    private Set<Exhibition> exhibitions;
    @NotNull
    @Comment("사용자 역활")
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}