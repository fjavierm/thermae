package dev.binarycoders.thermae.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "thermae.config")
public class ThermaeConfigProperties {

    private String server;
    private Long jwtExpirationTimeMillis;
}
