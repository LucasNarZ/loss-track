package com.lucasnarloch.loss_track.tenant;

import com.lucasnarloch.loss_track.domain.tenant.Tenant;
import com.lucasnarloch.loss_track.domain.tenant.TenantPlan;
import com.lucasnarloch.loss_track.domain.tenant.TenantRepository;
import com.lucasnarloch.loss_track.domain.tenant.TenantService;
import com.lucasnarloch.loss_track.domain.tenant.TenantStatus;
import com.lucasnarloch.loss_track.domain.tenant.dtos.TenantRequestDTO;
import com.lucasnarloch.loss_track.domain.tenant.dtos.TenantResponseDTO;
import com.lucasnarloch.loss_track.domain.tenant.exceptions.TenantNotFound;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TenantServiceTest {

    @Mock
    private TenantRepository tenantRepository;

    @InjectMocks
    private TenantService tenantService;

    @Test
    void saveShouldCreateTenantAndReturnResponseDto() {
        TenantRequestDTO request = new TenantRequestDTO(
                "Empresa LTDA",
                "12345678000199",
                TenantPlan.PRO,
                TenantStatus.ACTIVE
        );

        when(tenantRepository.save(any(Tenant.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        TenantResponseDTO response = tenantService.save(request);

        assertEquals("Empresa LTDA", response.name());
        assertEquals("12345678000199", response.cnpj());
        assertEquals(TenantPlan.PRO, response.plan());
        assertEquals(TenantStatus.ACTIVE, response.status());

        ArgumentCaptor<Tenant> tenantCaptor = ArgumentCaptor.forClass(Tenant.class);
        verify(tenantRepository).save(tenantCaptor.capture());

        Tenant savedTenant = tenantCaptor.getValue();
        assertEquals("Empresa LTDA", savedTenant.getName());
        assertEquals("12345678000199", savedTenant.getCnpj());
        assertEquals(TenantPlan.PRO, savedTenant.getPlan());
        assertEquals(TenantStatus.ACTIVE, savedTenant.getStatus());
    }

    @Test
    void findByIdShouldReturnResponseDtoWhenTenantExists() {
        UUID id = UUID.randomUUID();
        Tenant tenant = new Tenant("Empresa LTDA", "12345678000199", TenantPlan.PRO, TenantStatus.ACTIVE);

        when(tenantRepository.findById(id)).thenReturn(Optional.of(tenant));

        TenantResponseDTO response = tenantService.findById(id);

        assertEquals("Empresa LTDA", response.name());
        assertEquals("12345678000199", response.cnpj());
        assertEquals(TenantPlan.PRO, response.plan());
        assertEquals(TenantStatus.ACTIVE, response.status());
    }

    @Test
    void findByIdShouldThrowTenantNotFoundWhenTenantDoesNotExist() {
        UUID id = UUID.randomUUID();

        when(tenantRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TenantNotFound.class, () -> tenantService.findById(id));
    }

    @Test
    void findAllShouldReturnResponseDtoList() {
        Tenant firstTenant = new Tenant("Empresa LTDA", "12345678000199", TenantPlan.PRO, TenantStatus.ACTIVE);
        Tenant secondTenant = new Tenant("Outra Empresa", "98765432000188", TenantPlan.BASIC, TenantStatus.TRIAL);

        when(tenantRepository.findAll()).thenReturn(List.of(firstTenant, secondTenant));

        List<TenantResponseDTO> response = tenantService.findAll();

        assertEquals(2, response.size());
        assertEquals("Empresa LTDA", response.get(0).name());
        assertEquals("Outra Empresa", response.get(1).name());
    }

    @Test
    void updateShouldUpdateTenantAndReturnResponseDtoWhenTenantExists() {
        UUID id = UUID.randomUUID();
        Tenant tenant = new Tenant("Empresa LTDA", "12345678000199", TenantPlan.BASIC, TenantStatus.TRIAL);
        TenantRequestDTO request = new TenantRequestDTO(
                "Empresa Atualizada",
                "11222333000144",
                TenantPlan.ENTERPRISE,
                TenantStatus.SUSPENDED
        );

        when(tenantRepository.findById(id)).thenReturn(Optional.of(tenant));
        when(tenantRepository.save(tenant)).thenReturn(tenant);

        TenantResponseDTO response = tenantService.update(id, request);

        assertEquals("Empresa Atualizada", response.name());
        assertEquals("11222333000144", response.cnpj());
        assertEquals(TenantPlan.ENTERPRISE, response.plan());
        assertEquals(TenantStatus.SUSPENDED, response.status());
        assertEquals("Empresa Atualizada", tenant.getName());
        assertEquals("11222333000144", tenant.getCnpj());
        assertEquals(TenantPlan.ENTERPRISE, tenant.getPlan());
        assertEquals(TenantStatus.SUSPENDED, tenant.getStatus());

        verify(tenantRepository).save(tenant);
    }

    @Test
    void updateShouldThrowTenantNotFoundWhenTenantDoesNotExist() {
        UUID id = UUID.randomUUID();
        TenantRequestDTO request = new TenantRequestDTO(
                "Empresa Atualizada",
                "11222333000144",
                TenantPlan.ENTERPRISE,
                TenantStatus.SUSPENDED
        );

        when(tenantRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TenantNotFound.class, () -> tenantService.update(id, request));
        verify(tenantRepository, never()).save(any(Tenant.class));
    }

    @Test
    void deleteShouldDeleteTenantWhenTenantExists() {
        UUID id = UUID.randomUUID();
        Tenant tenant = new Tenant("Empresa LTDA", "12345678000199", TenantPlan.PRO, TenantStatus.ACTIVE);

        when(tenantRepository.findById(id)).thenReturn(Optional.of(tenant));

        tenantService.delete(id);

        verify(tenantRepository).delete(tenant);
    }

    @Test
    void deleteShouldThrowTenantNotFoundWhenTenantDoesNotExist() {
        UUID id = UUID.randomUUID();

        when(tenantRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(TenantNotFound.class, () -> tenantService.delete(id));
        verify(tenantRepository, never()).delete(any(Tenant.class));
    }
}
