package heck.jokenponline.auth.internal.infra.security.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import heck.jokenponline.auth.internal.domain.entity.User;
import heck.jokenponline.auth.internal.infra.security.exceptions.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.converter.RsaKeyConverters;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Component
public class TokenConfig {

    private final SecurityVariables securityVariables;

    public TokenConfig(SecurityVariables securityVariables) {
        this.securityVariables = securityVariables;
    }

    public String generateToken(User user) {
        Algorithm algorithm = Algorithm
                .RSA256(publicKeyFormatter(securityVariables.publicKey()),
                        privateKeyFormatter(securityVariables.privateKey()));

        return JWT.create()
                .withClaim("userUuid", user.getUuid().toString())
                .withClaim("roles", user.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                .withSubject(user.getUsername())
                .withExpiresAt(Instant.now().plus(securityVariables.tokenExpirationTimeMinutes(), ChronoUnit.MINUTES))
                .sign(algorithm);
    }

    public Optional<JWTUserData> validateToken(String token) {

        Algorithm algorithm = Algorithm
                .RSA256(publicKeyFormatter(securityVariables.publicKey()),
                        privateKeyFormatter(securityVariables.privateKey()));

        try {
            DecodedJWT decode = JWT.require(algorithm)
                    .build().verify(token);

            return Optional.of(JWTUserData.builder()
                    .uuid(UUID.fromString(decode.getClaim("userUuid").asString()))
                    .username(decode.getSubject())
                    .roles(decode.getClaim("roles").asList(String.class))
                    .build()
            );
        } catch (JWTVerificationException e) {
            throw new InvalidTokenException();
        }
    }

    private RSAPrivateKey privateKeyFormatter(String privateKey) {
        String privateKeyStringFormatted = securityVariables.privateKey()
                .replace("\\n", "\n").replace("\\r", "");
        ByteArrayInputStream privateKeyStream = new ByteArrayInputStream(privateKeyStringFormatted.getBytes(StandardCharsets.UTF_8));
        return RsaKeyConverters.pkcs8().convert(privateKeyStream);
    }

    private RSAPublicKey publicKeyFormatter(String publicKey) {
        String publicKeyStringFormatted = securityVariables.publicKey()
                .replace("\\n", "\n").replace("\\r", "");
        ByteArrayInputStream publicKeyStream = new ByteArrayInputStream(publicKeyStringFormatted.getBytes(StandardCharsets.UTF_8));
        return RsaKeyConverters.x509().convert(publicKeyStream);
    }
}
