package com.lucasnarloch.loss_track.domain.store;

import com.lucasnarloch.loss_track.domain.tenant.Tenant;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Getter
@Entity
@Table(
        name = "stores",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_store_tenant_code", columnNames = {"tenant_id", "code"}),
        }
)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false)
    private String code;

    @Column(nullable = false, unique = true, length = 14)
    private String cnpj;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false, length = 2)
    private String state;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StoreStatus status = StoreStatus.ACTIVE;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Store() {}

    public Store(
            Tenant tenant,
            String name,
            String code,
            String cnpj,
            String address,
            String city,
            String state
    ) {
        this.tenant = tenant;
        this.name = name;
        this.code = code;
        this.cnpj = cnpj;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public void update(String name, String code, String cnpj, String address, String city, String state) {
        this.name = name;
        this.code = code;
        this.cnpj = cnpj;
        this.address = address;
        this.city = city;
        this.state = state;
    }

    public void activate() {
        this.status = StoreStatus.ACTIVE;
    }

    public void deactivate() {
        this.status = StoreStatus.INACTIVE;
    }

}
