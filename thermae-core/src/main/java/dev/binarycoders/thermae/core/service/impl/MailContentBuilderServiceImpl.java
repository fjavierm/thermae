package dev.binarycoders.thermae.core.service.impl;

import dev.binarycoders.thermae.core.service.MailContentBuilderService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilderServiceImpl implements MailContentBuilderService {

    @Value("${thermae.config.server}")
    private String serverUrl;

    private final TemplateEngine templateEngine;

    @Override
    public String build(final String token) {
        final String verificationUrl = serverUrl + "/api/auth/account-verification";
        final Context context = new Context();

        context.setVariable("url", verificationUrl);
        context.setVariable("token", token);

        return templateEngine.process("mailTemplate", context);
    }
}
