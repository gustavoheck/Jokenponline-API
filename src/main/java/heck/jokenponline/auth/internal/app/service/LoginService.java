package heck.jokenponline.auth.internal.app.service;

import heck.jokenponline.auth.internal.domain.entity.User;
import heck.jokenponline.auth.internal.dto.login.LoginRequestDTO;
import heck.jokenponline.auth.internal.dto.login.LoginResponseDTO;
import heck.jokenponline.auth.internal.infra.security.config.TokenConfig;
import heck.jokenponline.auth.internal.infra.security.exception.InvalidLoginException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public LoginService(AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    @Transactional
    public LoginResponseDTO login (LoginRequestDTO request) {
        UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.username(), request.password());

        try {
            Authentication authentication = authenticationManager.authenticate(userAndPass);

            User user = (User) authentication.getPrincipal();

            String token = tokenConfig.generateToken(user);

            return new LoginResponseDTO(token);
        } catch (UsernameNotFoundException | BadCredentialsException e) {
            throw new InvalidLoginException();
        }

    }
}
