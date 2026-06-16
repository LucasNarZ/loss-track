package com.lucasnarloch.loss_track.domain.user.dtos;

import com.lucasnarloch.loss_track.domain.user.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record UserRequestDTO(
        @NotNull UUID tenantId,
        @NotBlank String name,
        @NotBlank @Email String email,
        @NotBlank String password,
        @NotNull UserStatus status
) {
}
