package dev.binarycoders.thermae.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String username;
    private String authenticationToken;
    private Instant expiresAt;
    private String refreshToken;
}
