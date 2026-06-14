package com.lucasnarloch.loss_track.domain.store.dtos;

import com.lucasnarloch.loss_track.domain.store.Store;
import com.lucasnarloch.loss_track.domain.store.StoreStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record StoreResponseDTO(
        UUID id,
        UUID tenantId,
        String name,
        String code,
        String cnpj,
        String address,
        String city,
        String state,
        StoreStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public StoreResponseDTO(Store store) {
        this(
                store.getId(),
                store.getTenant().getId(),
                store.getName(),
                store.getCode(),
                store.getCnpj(),
                store.getAddress(),
                store.getCity(),
                store.getState(),
                store.getStatus(),
                store.getCreatedAt(),
                store.getUpdatedAt()
        );
    }
}
