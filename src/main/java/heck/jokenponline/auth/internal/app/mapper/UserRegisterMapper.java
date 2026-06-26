package heck.jokenponline.auth.internal.app.mapper;

import heck.jokenponline.auth.internal.domain.entity.User;
import heck.jokenponline.auth.internal.dto.register.RegisterRequestDTO;
import heck.jokenponline.auth.internal.dto.register.RegisterResponseDTO;
import org.springframework.stereotype.Component;

@Component
public class UserRegisterMapper {
    public User toEntity (RegisterRequestDTO request) {
        return new User(
                request.username(),
                request.email(),
                request.password()
        );
    }

    public RegisterResponseDTO toResponse (User user) {
        return new RegisterResponseDTO(
                user.getUuid(),
                user.getUsername(),
                user.getEmail()
        );
    }
}
