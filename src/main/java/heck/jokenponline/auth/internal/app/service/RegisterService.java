package heck.jokenponline.auth.internal.app.service;

import heck.jokenponline.auth.internal.app.mapper.UserRegisterMapper;
import heck.jokenponline.auth.internal.domain.entity.Role;
import heck.jokenponline.auth.internal.domain.entity.User;
import heck.jokenponline.auth.internal.domain.enums.Roles;
import heck.jokenponline.auth.internal.dto.register.RegisterRequestDTO;
import heck.jokenponline.auth.internal.dto.register.RegisterResponseDTO;
import heck.jokenponline.auth.internal.infra.repository.RoleRepository;
import heck.jokenponline.auth.internal.infra.repository.UserRepository;
import heck.jokenponline.auth.internal.infra.security.exceptions.NotExistentRole;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class RegisterService {

    private final UserRegisterMapper userRegisterMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public RegisterService(UserRegisterMapper userRegisterMapper, UserRepository userRepository, RoleRepository roleRepository) {
        this.userRegisterMapper = userRegisterMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Transactional
    public RegisterResponseDTO register (@Valid @RequestBody RegisterRequestDTO request) {
        User user = userRegisterMapper.toEntity(request);

        Role roleUser = roleRepository.findByRole(Roles.ROLE_USER.toString())
                        .orElseThrow(() -> new NotExistentRole("The role %s is not registered in the database".formatted(Roles.ROLE_USER.toString())));

        user.getRoles().add(roleUser);

        userRepository.save(user);

        return userRegisterMapper.toResponse(user);
    }
}
