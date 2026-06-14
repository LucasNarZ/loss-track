package com.lucasnarloch.loss_track.domain.tenant;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Entity
@Table(name = "tenants")
public class Tenant {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TenantPlan plan = TenantPlan.BASIC;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TenantStatus status = TenantStatus.TRIAL;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Tenant() {}

    public Tenant(String name, String cnpj, TenantPlan plan) {
        this.name = name;
        this.cnpj = cnpj;
        this.plan = plan;
    }

    public Tenant(String name, String cnpj, TenantPlan plan, TenantStatus status) {
        this.name = name;
        this.cnpj = cnpj;
        this.plan = plan;
        this.status = status;
    }

    public void update(String name, String cnpj, TenantPlan plan, TenantStatus status) {
        this.name = name;
        this.cnpj = cnpj;
        this.plan = plan;
        this.status = status;
    }
}