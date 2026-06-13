package com.lucasnarloch.loss_track.product.exceptions;

import com.lucasnarloch.loss_track.exceptions.NotFoundException;

public class ProductNotFound extends NotFoundException {
    public ProductNotFound() {
        super("Product not found.");
    }
}
