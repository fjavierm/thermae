/*
 * MIT License
 *
 * Copyright (c) 2020 fjavierm
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR
 * PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT
 * OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
 * DEALINGS IN THE SOFTWARE.
 */

package dev.binarycoders.thermae.core.security.impl;

import dev.binarycoders.thermae.core.config.ThermaeConfigProperties;
import dev.binarycoders.thermae.core.exception.ThermaeException;
import dev.binarycoders.thermae.core.persistence.repository.UserRepository;
import dev.binarycoders.thermae.core.security.JwtProvider;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtProviderImpl implements JwtProvider {

    private KeyStore keyStore;

    private final ThermaeConfigProperties thermaeConfigProperties;
    private final UserRepository userRepository;

    public JwtProviderImpl(final ThermaeConfigProperties thermaeConfigProperties, final UserRepository userRepository) {
        this.thermaeConfigProperties = thermaeConfigProperties;
        this.userRepository = userRepository;
    }

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
            .setExpiration(Date.from(Instant.now().plusMillis(thermaeConfigProperties.getJwtExpirationTimeMillis())))
            .compact();
    }

    @Override
    public String generateTokenWithUserId(final Long userId) {
        final var user = userRepository.findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return Jwts.builder()
            .setSubject(user.getUsername())
            .signWith(getPrivateKey())
            .setExpiration(Date.from(Instant.now().plusMillis(thermaeConfigProperties.getJwtExpirationTimeMillis())))
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

    @Override
    public Long getJwtExpirationTimeMillis() {
        return thermaeConfigProperties.getJwtExpirationTimeMillis();
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
