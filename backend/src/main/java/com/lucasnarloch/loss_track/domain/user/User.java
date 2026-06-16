package com.lucasnarloch.loss_track.domain.user;

import com.lucasnarloch.loss_track.domain.tenant.Tenant;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_user_tenant_email", columnNames = {"tenant_id", "email"}),
        }
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id", nullable = false)
    private Tenant tenant;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private UserStatus status = UserStatus.ACTIVE;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    protected User() {}

    public User(Tenant tenant, String name, String email, String passwordHash, UserStatus status) {
        this.tenant = tenant;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
    }

    public void update(String name, String email, String passwordHash, UserStatus status) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.status = status;
    }
}
