package dev.binarycoders.thermae.core.service.impl;

import dev.binarycoders.thermae.core.exception.ThermaeException;
import dev.binarycoders.thermae.core.model.NotificationEmail;
import dev.binarycoders.thermae.core.service.MailContentBuilderService;
import dev.binarycoders.thermae.core.service.MailService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class MailServiceImpl implements MailService {

    private final JavaMailSender sender;
    private final MailContentBuilderService mailContentBuilderService;

    @Async
    @Override
    public void sendMail(NotificationEmail notification) {
        final MimeMessagePreparator preparator = mimeMessage -> {
            final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);

            helper.setFrom("signup@thermae.org");
            helper.setTo(notification.getRecipient());
            helper.setSubject(notification.getSubject());
            helper.setText(mailContentBuilderService.build(notification.getBody()));
        };

        try {
            sender.send(preparator);
            log.info("Activation email for user with email {} sent", notification.getRecipient());
        } catch (final MailException e) {
            throw new ThermaeException(String.format("Exception occurred when sending email to address %s", notification.getRecipient()), e);
        }
    }
}
