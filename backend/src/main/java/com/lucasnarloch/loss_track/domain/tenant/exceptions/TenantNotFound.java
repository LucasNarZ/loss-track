package com.lucasnarloch.loss_track.domain.tenant.exceptions;

import com.lucasnarloch.loss_track.exceptions.NotFoundException;

public class TenantNotFound extends NotFoundException {
    public TenantNotFound() {
        super("Tenant not found.");
    }
}
