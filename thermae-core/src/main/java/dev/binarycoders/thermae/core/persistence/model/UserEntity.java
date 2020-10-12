package dev.binarycoders.thermae.core.persistence.model;

import dev.binarycoders.thermae.core.ThermaeCoreConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@Entity
@Builder
@Table(name = "users", schema = ThermaeCoreConstants.SCHEMA)
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Username is required")
    @Column(nullable = false)
    private String username;

    @NotBlank(message = "Password is required")
    @Column(nullable = false)
    private String password;

    @Email
    @NotBlank(message = "Email is required")
    @Column(nullable = false)
    private String email;

    @Column(nullable = false, updatable = false)
    private Instant created;

    @Column(nullable = false)
    private Boolean enabled;
}
