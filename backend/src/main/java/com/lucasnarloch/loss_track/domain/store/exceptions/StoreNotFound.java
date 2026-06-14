package com.lucasnarloch.loss_track.domain.store.exceptions;

import com.lucasnarloch.loss_track.exceptions.NotFoundException;

public class StoreNotFound extends NotFoundException {
    public StoreNotFound() {
        super("Store not found.");
    }
}
