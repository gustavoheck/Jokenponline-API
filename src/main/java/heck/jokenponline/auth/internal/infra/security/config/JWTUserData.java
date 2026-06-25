package heck.jokenponline.auth.internal.infra.security.config;

import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;
import java.util.UUID;

@Builder
public record JWTUserData(UUID uuid, String username, List<String> roles) {
}
