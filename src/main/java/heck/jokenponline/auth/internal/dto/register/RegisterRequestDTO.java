package heck.jokenponline.auth.internal.dto.register;

import heck.jokenponline.auth.internal.dto.validation.password.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequestDTO(

        @NotBlank(message = "The username cannot be null")
        String username,

        @Email(message = "The email must be valid")
        @NotBlank(message = "The email cannot be blank")
        String email,

        @Size(min = 8, message = "The password must contain at least 8 characters")
        @NotBlank(message = "The message cannot be null")
        @ValidPassword
        String password
) {
}
