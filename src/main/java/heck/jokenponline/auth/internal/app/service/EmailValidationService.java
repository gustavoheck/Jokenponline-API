package heck.jokenponline.auth.internal.app.service;

import heck.jokenponline.auth.internal.app.exceptions.InvalidEmailTokenException;
import heck.jokenponline.auth.internal.domain.entity.User;
import heck.jokenponline.auth.internal.infra.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class EmailValidationService {

    private final UserRepository userRepository;

    public EmailValidationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void validateEmailToken (User user) {
        while (true) {
            Optional<User> userWithSameToken = userRepository.findByToken(user.getToken());
            boolean isTokenUsed;
            if (userWithSameToken.isPresent()) {
                isTokenUsed = userWithSameToken.get().isTokenUsed();
            } else {
                isTokenUsed = false;
            }

            if (isTokenUsed) {
                user.generateNewToken();
            } else {
                break;
            }
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
