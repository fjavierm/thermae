package dev.binarycoders.thermae.core.service.impl;

import dev.binarycoders.thermae.core.exception.ThermaeException;
import dev.binarycoders.thermae.core.service.JwtProviderService;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;

@Service
public class JwtProviderServiceImpl implements JwtProviderService {

    private KeyStore keyStore;

    @PostConstruct
    public void init() {
        try {
            final InputStream resourceAsStream = getClass().getResourceAsStream("/thermae.jks"); // TODO This needs to be created

            keyStore = KeyStore.getInstance("JKS");
            keyStore.load(resourceAsStream, "secret".toCharArray());
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

    private PrivateKey getPrivateKey() {
        try {
            return (PrivateKey) keyStore.getKey("thermae", "secret".toCharArray()); // Move this to a property file
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new ThermaeException("Exception occurred while retrieving public key from keystore", e);
        }
    }
}
