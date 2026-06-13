package com.lucasnarloch.loss_track.product.dtos;

import com.lucasnarloch.loss_track.product.Product;

import java.util.UUID;

public record ProductResponseDTO(
        UUID id,
        String name,
        String barcode,
        String category
) {
    public ProductResponseDTO(Product product) {
        this(
                product.getId(),
                product.getName(),
                product.getBarcode(),
                product.getCategory()
        );
    }
}