package com.lucasnarloch.loss_track.domain.user.exceptions;

import com.lucasnarloch.loss_track.exceptions.NotFoundException;

public class UserNotFound extends NotFoundException {
    public UserNotFound() {
        super("User not found");
    }
}
