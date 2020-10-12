package dev.binarycoders.thermae.core.persistence.model;

import dev.binarycoders.thermae.core.ThermaeCoreConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;

@Data
@Entity
@Builder
@Table(name = "verification_tokens", schema = ThermaeCoreConstants.SCHEMA)
@NoArgsConstructor
@AllArgsConstructor
public class VerificationTokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false)
    private String token;

    @Column(nullable = false, updatable = false)
    private Instant expiryDate;

    @OneToOne(fetch = FetchType.LAZY)
    private UserEntity user;
}
