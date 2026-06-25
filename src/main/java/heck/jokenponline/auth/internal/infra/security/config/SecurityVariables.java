package heck.jokenponline.auth.internal.infra.security.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@ConfigurationProperties(prefix = "security.variables")
public record SecurityVariables (

        @Value("${spring.security.oauth2.resourceserver.jwt.private-key-location}")
        RSAPrivateKey privateKey,

        @Value("${spring.security.oauth2.resourceserver.jwt.public-key-location}")
        RSAPublicKey publicKey,

        Integer tokenExpirationTimeMinutes
) {
}
