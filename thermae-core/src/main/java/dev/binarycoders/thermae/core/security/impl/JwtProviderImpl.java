package dev.binarycoders.thermae.core.security.impl;

import dev.binarycoders.thermae.core.exception.ThermaeException;
import dev.binarycoders.thermae.core.security.JwtProvider;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Component
public class JwtProviderImpl implements JwtProvider {

    private KeyStore keyStore;

    @PostConstruct
    public void init() {
        try {
            final InputStream resourceAsStream = getClass().getResourceAsStream("/thermae.jks");

            keyStore = KeyStore.getInstance("JKS");
            keyStore.load(resourceAsStream, "secret".toCharArray()); // TODO Move to a property
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new ThermaeException("Exception occurred while loading keystore", e);
        }

    }

    @Override
    public String generateToken(final Authentication authentication) {
        final var principal = (User) authentication.getPrincipal();

        return Jwts.builder()
            .setSubject(principal.getUsername())
            .signWith(getPrivateKey())
            .compact();
    }

    @Override
    public boolean validateToken(final String token) {
        final var parser = Jwts.parserBuilder()
            .setSigningKey(getPublicKey()).build();

        parser.parseClaimsJws(token);

        return true;
    }

    @Override
    public String getUsernameFromToken(final String token) {
        final var parser = Jwts.parserBuilder()
            .setSigningKey(getPublicKey()).build();
        final var claims = parser.parseClaimsJws(token).getBody();

        return claims.getSubject();
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("thermae").getPublicKey();
        } catch (KeyStoreException e) {
            throw new ThermaeException("Exception occurred while retrieving public key");
        }
    }

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("thermae", "secret".toCharArray()); // Move this to a property file
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new ThermaeException("Exception occurred while retrieving public key from keystore", e);
        }
    }
}
