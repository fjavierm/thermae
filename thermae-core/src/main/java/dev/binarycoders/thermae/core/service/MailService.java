package dev.binarycoders.thermae.core.service;

import dev.binarycoders.thermae.core.model.NotificationEmail;

public interface MailService {
    void sendMail(NotificationEmail notification);
}
