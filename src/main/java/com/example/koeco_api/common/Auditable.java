package com.example.koeco_api.common;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract  class Auditable {
    @Column(name = "create_date", updatable = false)
    private LocalDateTime create_Date;

    @Column(name = "create_by", updatable = false)
    private String create_By;

    @Column(name = "modifier_date")
    private LocalDateTime modifier_Date;

    @Column(name = "modifier_by")
    private String modifier_By;

    @Column(name = "is_delete")
    private Boolean is_Delete;
    @PrePersist
    protected void onCreate() {
        this.create_Date = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        this.modifier_Date = LocalDateTime.now();
    }
}
