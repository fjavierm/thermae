package dev.binarycoders.thermae.core.service.impl;

import dev.binarycoders.thermae.core.model.NotificationEmail;
import dev.binarycoders.thermae.core.persistence.model.UserEntity;
import dev.binarycoders.thermae.core.persistence.model.VerificationTokenEntity;
import dev.binarycoders.thermae.core.persistence.repository.UserRepository;
import dev.binarycoders.thermae.core.persistence.repository.VerificationTokenRepository;
import dev.binarycoders.thermae.core.service.AuthService;
import dev.binarycoders.thermae.core.service.MailService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final int TWO_HOURS_EXPIRY_DATE = 2;

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Override
    @Transactional
    public void signup(final String username, final String email, final String password) {
        final var user = UserEntity.builder()
            .username(username)
            .email(email)
            .password(passwordEncoder.encode(password))
            .created(Instant.now())
            .enabled(false)
            .build();

        userRepository.save(user);

        final var token = generateVerificationToken(user);
        final var notification = NotificationEmail.builder()
            .subject("Please activate your account")
            .recipient(user.getEmail())
            .body(token)
            .build();

        mailService.sendMail(notification); // TODO Move to a spring event
    }

    private String generateVerificationToken(final UserEntity user) {
        final var token = UUID.randomUUID().toString();
        final var verificationToken = VerificationTokenEntity.builder()
            .user(user)
            .token(token)
            .expiryDate(Instant.now().plus(TWO_HOURS_EXPIRY_DATE, ChronoUnit.HOURS))
            .build();

        verificationTokenRepository.save(verificationToken);

        return token;
    }
}