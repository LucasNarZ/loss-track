package com.lucasnarloch.loss_track.domain.user;

import com.lucasnarloch.loss_track.domain.tenant.Tenant;
import com.lucasnarloch.loss_track.domain.tenant.TenantRepository;
import com.lucasnarloch.loss_track.domain.tenant.exceptions.TenantNotFound;
import com.lucasnarloch.loss_track.domain.user.dtos.UserRequestDTO;
import com.lucasnarloch.loss_track.domain.user.dtos.UserResponseDTO;
import com.lucasnarloch.loss_track.domain.user.exceptions.UserNotFound;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final TenantRepository tenantRepository;

    public UserService(UserRepository userRepository, TenantRepository tenantRepository) {
        this.userRepository = userRepository;
        this.tenantRepository = tenantRepository;
    }

    public UserResponseDTO save(UserRequestDTO userRequestDto) {
        Tenant tenant = findTenantById(userRequestDto.tenantId());
        User user = new User(
                tenant,
                userRequestDto.name(),
                userRequestDto.email(),
                userRequestDto.password(),
                userRequestDto.status()
        );
        return new UserResponseDTO(userRepository.save(user));
    }

    public UserResponseDTO findById(UUID id) {
        User user = findEntityById(id);
        return new UserResponseDTO(user);
    }

    public List<UserResponseDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserResponseDTO::new).toList();
    }

    public UserResponseDTO update(UUID id, UserRequestDTO userRequestDto) {
        User user = findEntityById(id);
        user.update(
                userRequestDto.name(),
                userRequestDto.email(),
                userRequestDto.password(),
                userRequestDto.status()
        );
        return new UserResponseDTO(userRepository.save(user));
    }

    public void delete(UUID id) {
        User user = findEntityById(id);
        userRepository.delete(user);
    }

    private User findEntityById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFound::new);
    }

    private Tenant findTenantById(UUID id) {
        return tenantRepository.findById(id).orElseThrow(TenantNotFound::new);
    }
}
