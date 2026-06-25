package heck.jokenponline.auth.internal.controller;

import heck.jokenponline.auth.internal.app.LoginUseCase;
import heck.jokenponline.auth.internal.dto.login.LoginRequestDTO;
import heck.jokenponline.auth.internal.dto.login.LoginResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final LoginUseCase loginUseCase;

    public AuthController(LoginUseCase loginUseCase) {
        this.loginUseCase = loginUseCase;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = loginUseCase.login(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
