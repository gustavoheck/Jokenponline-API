package heck.jokenponline.auth.internal.controller;

import heck.jokenponline.auth.internal.app.service.LoginService;
import heck.jokenponline.auth.internal.app.service.RegisterService;
import heck.jokenponline.auth.internal.dto.login.LoginRequestDTO;
import heck.jokenponline.auth.internal.dto.login.LoginResponseDTO;
import heck.jokenponline.auth.internal.dto.register.RegisterRequestDTO;
import heck.jokenponline.auth.internal.dto.register.RegisterResponseDTO;
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

    private final LoginService loginService;
    private final RegisterService registerService;

    public AuthController(LoginService loginService, RegisterService registerService) {
        this.loginService = loginService;
        this.registerService = registerService;
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponseDTO> register (@Valid @RequestBody RegisterRequestDTO request) {
        RegisterResponseDTO response = registerService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login (@Valid @RequestBody LoginRequestDTO request) {
        LoginResponseDTO response = loginService.login(request);

        return ResponseEntity.status(HttpStatus.OK)
                .body(response);
    }
}
