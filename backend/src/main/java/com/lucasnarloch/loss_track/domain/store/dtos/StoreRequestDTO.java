package com.lucasnarloch.loss_track.domain.store.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record StoreRequestDTO(
        @NotNull UUID tenantId,
        @NotBlank String name,
        @NotBlank String code,
        @NotBlank String cnpj,
        @NotBlank String address,
        @NotBlank String city,
        @NotBlank String state
) {
}
