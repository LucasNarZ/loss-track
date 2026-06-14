package com.lucasnarloch.loss_track.domain.tenant.dtos;

import com.lucasnarloch.loss_track.domain.tenant.Tenant;
import com.lucasnarloch.loss_track.domain.tenant.TenantPlan;
import com.lucasnarloch.loss_track.domain.tenant.TenantStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TenantResponseDTO(
        UUID id,
        String name,
        String cnpj,
        TenantPlan plan,
        TenantStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public TenantResponseDTO(Tenant tenant) {
        this(
                tenant.getId(),
                tenant.getName(),
                tenant.getCnpj(),
                tenant.getPlan(),
                tenant.getStatus(),
                tenant.getCreatedAt(),
                tenant.getUpdatedAt()
        );
    }
}
