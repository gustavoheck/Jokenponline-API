package heck.jokenponline.auth.internal.app.service;

import heck.jokenponline.auth.internal.app.exceptions.InvalidEmailTokenException;
import heck.jokenponline.auth.internal.domain.entity.User;
import heck.jokenponline.auth.internal.infra.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmailValidationService {

    private final UserRepository userRepository;

    public EmailValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateEmailToken (User user) {
        List<User> usersWithSameToken = userRepository.findByToken(user.getToken());
        if (usersWithSameToken.size() > 1) {
            throw new InvalidEmailTokenException("This token is already used! Generate another token");
        }
    }

    public void validateUser (User user) {
        if (user.validateUser() == false) {
            throw new InvalidEmailTokenException("The token for e-mail validation is expired");
        }
    }

    public void createNewEmailValidationToken (User user) {
        user.generateNewToken();
        validateEmailToken(user);
    }
}
