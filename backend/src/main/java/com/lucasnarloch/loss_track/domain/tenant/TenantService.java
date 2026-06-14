package com.lucasnarloch.loss_track.domain.tenant;

import com.lucasnarloch.loss_track.domain.tenant.dtos.TenantRequestDTO;
import com.lucasnarloch.loss_track.domain.tenant.dtos.TenantResponseDTO;
import com.lucasnarloch.loss_track.domain.tenant.exceptions.TenantNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TenantService {
    private final TenantRepository tenantRepository;

    public TenantService(TenantRepository tenantRepository) {
        this.tenantRepository = tenantRepository;
    }

    public TenantResponseDTO save(TenantRequestDTO tenantRequestDto) {
        Tenant tenant = new Tenant(
                tenantRequestDto.name(),
                tenantRequestDto.cnpj(),
                tenantRequestDto.plan(),
                tenantRequestDto.status()
        );
        return new TenantResponseDTO(tenantRepository.save(tenant));
    }

    public TenantResponseDTO findById(UUID id) {
        Tenant tenant = findEntityById(id);
        return new TenantResponseDTO(tenant);
    }

    public List<TenantResponseDTO> findAll() {
        List<Tenant> tenants = tenantRepository.findAll();
        return tenants.stream().map(TenantResponseDTO::new).toList();
    }

    public TenantResponseDTO update(UUID id, TenantRequestDTO tenantRequestDto) {
        Tenant tenant = findEntityById(id);
        tenant.update(
                tenantRequestDto.name(),
                tenantRequestDto.cnpj(),
                tenantRequestDto.plan(),
                tenantRequestDto.status()
        );
        return new TenantResponseDTO(tenantRepository.save(tenant));
    }

    public void delete(UUID id) {
        Tenant tenant = findEntityById(id);
        tenantRepository.delete(tenant);
    }

    private Tenant findEntityById(UUID id) {
        return tenantRepository.findById(id).orElseThrow(TenantNotFound::new);
    }
}
