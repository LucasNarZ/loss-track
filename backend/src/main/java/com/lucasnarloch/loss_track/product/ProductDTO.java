package com.lucasnarloch.loss_track.product;

import jakarta.validation.constraints.NotBlank;

public record ProductDTO(
        @NotBlank String name,
        @NotBlank String barcode,
        @NotBlank String category
) {
}
