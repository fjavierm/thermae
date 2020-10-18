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
