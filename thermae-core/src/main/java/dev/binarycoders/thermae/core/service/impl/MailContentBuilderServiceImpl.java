package dev.binarycoders.thermae.core.service.impl;

import dev.binarycoders.thermae.core.config.ThermaeConfigProperties;
import dev.binarycoders.thermae.core.service.MailContentBuilderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
@AllArgsConstructor
public class MailContentBuilderServiceImpl implements MailContentBuilderService {

    private final ThermaeConfigProperties thermaeConfigProperties;
    private final TemplateEngine templateEngine;

    @Override
    public String build(final String token) {
        final String verificationUrl = thermaeConfigProperties.getServer() + "/api/auth/account-verification";
        final Context context = new Context();

        context.setVariable("url", verificationUrl);
        context.setVariable("token", token);

        return templateEngine.process("mailTemplate", context);
    }
}
