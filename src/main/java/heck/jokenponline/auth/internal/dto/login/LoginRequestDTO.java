package heck.jokenponline.auth.internal.dto.login;

import jakarta.validation.constraints.NotBlank;

public record LoginRequestDTO(

        @NotBlank(message = "Username cannot be null or blank")
        String username,

        @NotBlank(message = "Password cannot be null or blank")
        String password
) {
}
