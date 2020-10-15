package dev.binarycoders.thermae.core.security;

import org.springframework.security.core.Authentication;

public interface JwtProvider {

    String generateToken(Authentication authentication);

    boolean validateToken(String jwt);

    String getUsernameFromToken(String token);
}
