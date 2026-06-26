package heck.jokenponline.auth.internal.app.usecase;

import heck.jokenponline.auth.internal.app.mapper.UserRegisterMapper;
import heck.jokenponline.auth.internal.domain.entity.Role;
import heck.jokenponline.auth.internal.domain.entity.User;
import heck.jokenponline.auth.internal.domain.enums.Roles;
import heck.jokenponline.auth.internal.dto.register.RegisterRequestDTO;
import heck.jokenponline.auth.internal.dto.register.RegisterResponseDTO;
import heck.jokenponline.auth.internal.infra.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterService {

    private final UserRegisterMapper userRegisterMapper;
    private final UserRepository userRepository;

    public RegisterService(UserRegisterMapper userRegisterMapper, UserRepository userRepository) {
        this.userRegisterMapper = userRegisterMapper;
        this.userRepository = userRepository;
    }

    @Transactional
    public RegisterResponseDTO register (@Valid @RequestBody RegisterRequestDTO request) {
        User user = userRegisterMapper.toEntity(request);

        user.getRoles().add(new Role(Roles.ROLE_USER.toString()));

        userRepository.save(user);

        return userRegisterMapper.toResponse(user);
    }
}
