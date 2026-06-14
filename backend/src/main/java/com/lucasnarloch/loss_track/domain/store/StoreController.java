package com.lucasnarloch.loss_track.domain.store;

import com.lucasnarloch.loss_track.domain.store.dtos.StoreRequestDTO;
import com.lucasnarloch.loss_track.domain.store.dtos.StoreResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StoreResponseDTO save(@Valid @RequestBody StoreRequestDTO storeRequestDto) {
        return storeService.save(storeRequestDto);
    }

    @GetMapping("/{id}")
    public StoreResponseDTO findById(@PathVariable UUID id) {
        return storeService.findById(id);
    }

    @GetMapping
    public List<StoreResponseDTO> findAll() {
        return storeService.findAll();
    }

    @PutMapping("/{id}")
    public StoreResponseDTO update(@PathVariable UUID id, @Valid @RequestBody StoreRequestDTO storeRequestDto) {
        return storeService.update(id, storeRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        storeService.delete(id);
    }
}
