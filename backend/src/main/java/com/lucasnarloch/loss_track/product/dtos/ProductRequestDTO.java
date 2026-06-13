package com.lucasnarloch.loss_track.product.dtos;

import jakarta.validation.constraints.NotBlank;

public record ProductRequestDTO(
        @NotBlank String name,
        @NotBlank String barcode,
        @NotBlank String category
) {
}
