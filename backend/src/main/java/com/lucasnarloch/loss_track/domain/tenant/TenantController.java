package com.lucasnarloch.loss_track.domain.tenant;

import com.lucasnarloch.loss_track.domain.tenant.dtos.TenantRequestDTO;
import com.lucasnarloch.loss_track.domain.tenant.dtos.TenantResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tenants")
public class TenantController {
    private final TenantService tenantService;

    public TenantController(TenantService tenantService) {
        this.tenantService = tenantService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TenantResponseDTO save(@Valid @RequestBody TenantRequestDTO tenantRequestDto) {
        return tenantService.save(tenantRequestDto);
    }

    @GetMapping("/{id}")
    public TenantResponseDTO findById(@PathVariable UUID id) {
        return tenantService.findById(id);
    }

    @GetMapping
    public List<TenantResponseDTO> findAll() {
        return tenantService.findAll();
    }

    @PutMapping("/{id}")
    public TenantResponseDTO update(@PathVariable UUID id, @Valid @RequestBody TenantRequestDTO tenantRequestDto) {
        return tenantService.update(id, tenantRequestDto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        tenantService.delete(id);
    }
}
