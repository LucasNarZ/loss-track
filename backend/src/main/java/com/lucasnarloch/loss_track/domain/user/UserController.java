package com.lucasnarloch.loss_track.domain.user;

import com.lucasnarloch.loss_track.domain.user.dtos.UserRequestDTO;
import com.lucasnarloch.loss_track.domain.user.dtos.UserResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDTO save(@Valid @RequestBody UserRequestDTO userRequestDto) {
        return userService.save(userRequestDto);
    }

    @GetMapping("/{id}")
    public UserResponseDTO findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @GetMapping
    public List<UserResponseDTO> findAll() {
        return userService.findAll();
    }

    @PutMapping("/{id}")
    public UserResponseDTO update(@PathVariable UUID id, @Valid @RequestBody UserRequestDTO userRequestDto) {
        return userService.update(id, userRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }
}
