package com.lucasnarloch.loss_track.domain.product.exceptions;

import com.lucasnarloch.loss_track.exceptions.NotFoundException;

public class ProductNotFound extends NotFoundException {
    public ProductNotFound() {
        super("Product not found.");
    }
}
