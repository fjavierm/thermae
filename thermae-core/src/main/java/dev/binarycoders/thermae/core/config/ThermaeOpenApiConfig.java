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

package dev.binarycoders.thermae.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThermaeOpenApiConfig {

    @Bean
    @ConfigurationProperties(prefix = "thermae.config.openapi")
    public ThermaeOpenApiProperties thermaeOpenApiProperties() {
        return new ThermaeOpenApiProperties();
    }

    @Bean
    public OpenAPI thermaeApi(final ThermaeOpenApiProperties thermaeOpenApiProperties) {
        return new OpenAPI()
            .info(new Info()
                .title(thermaeOpenApiProperties.getTitle())
                .version(thermaeOpenApiProperties.getVersion())
                .description(thermaeOpenApiProperties.getDescription())
                .license(new License()
                    .name("License MIT")
                    .url("http://binarycoders.dev"))
                .contact(new Contact()
                    .url("http://binarycoders.dev")
                    .name("fjavierm")));

    }
}
