package com.lucasnarloch.loss_track.domain.product.dtos;

import com.lucasnarloch.loss_track.domain.product.Product;
import com.lucasnarloch.loss_track.domain.product.ProductStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        UUID tenantId,
        String name,
        String sku,
        String barcode,
        String category,
        String unit,
        BigDecimal costPrice,
        ProductStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public ProductResponseDTO(Product product) {
        this(
                product.getId(),
                product.getTenant().getId(),
                product.getName(),
                product.getSku(),
                product.getBarcode(),
                product.getCategory(),
                product.getUnit(),
                product.getCostPrice(),
                product.getStatus(),
                product.getCreatedAt(),
                product.getUpdatedAt()
        );
    }
}
