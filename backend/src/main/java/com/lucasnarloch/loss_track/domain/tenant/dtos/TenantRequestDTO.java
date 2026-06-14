package com.lucasnarloch.loss_track.domain.tenant.dtos;

import com.lucasnarloch.loss_track.domain.tenant.TenantPlan;
import com.lucasnarloch.loss_track.domain.tenant.TenantStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TenantRequestDTO(
        @NotBlank String name,
        @NotBlank String cnpj,
        @NotNull TenantPlan plan,
        @NotNull TenantStatus status
) {
}
