package com.lucasnarloch.loss_track.domain.product;

import com.lucasnarloch.loss_track.domain.tenant.Tenant;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(
        name = "products",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_product_tenant_sku", columnNames = {"tenant_id", "sku"}),
        }
)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid")
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String sku;

    private String barcode;
    private String category;

    @Column(nullable = false)
    private String unit;

    @Column(name = "cost_price", nullable = false)
    private BigDecimal costPrice;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status = ProductStatus.ACTIVE;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected Product(){}

    public Product(Tenant tenant, String name, String sku, String barcode, String category, String unit, BigDecimal costPrice) {
        this.tenant = tenant;
        this.name = name;
        this.sku = sku;
        this.barcode = barcode;
        this.category = category;
        this.unit = unit;
        this.costPrice = costPrice;
    }

    public void update(String name, String sku, String barcode, String category, String unit, BigDecimal costPrice) {
        this.name = name;
        this.sku = sku;
        this.barcode = barcode;
        this.category = category;
        this.unit = unit;
        this.costPrice = costPrice;
    }

}
