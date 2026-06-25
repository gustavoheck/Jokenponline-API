package heck.jokenponline.auth.internal.dto.register;

import java.util.UUID;

public record RegisterResponseDTO (

        UUID uuid,

        String username,

        String email
) {
}
