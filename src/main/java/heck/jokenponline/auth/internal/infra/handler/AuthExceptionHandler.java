package heck.jokenponline.auth.internal.infra.handler;

import heck.jokenponline.auth.internal.app.exceptions.UsernameAlreadyUsedException;
import heck.jokenponline.auth.internal.infra.security.exception.InvalidLoginException;
import heck.jokenponline.auth.internal.infra.security.exception.InvalidTokenException;
import heck.jokenponline.auth.internal.infra.security.exception.NotExistentRoleException;
import heck.jokenponline.shared.infra.handler.DefaultExceptionHandler;
import heck.jokenponline.shared.infra.handler.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice(basePackages = "heck.jokenponline.auth")
public class AuthExceptionHandler extends DefaultExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    private ResponseEntity<ErrorResponseDTO> invalidTokenHandler (InvalidTokenException exception, WebRequest request) {

        String path = request.getDescription(false).replaceAll("uri=", "");

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(new ErrorResponseDTO(
                        Instant.now().toString(),
                        HttpStatus.UNAUTHORIZED.value(),
                        HttpStatus.UNAUTHORIZED.toString(),
                        exception.getMessage(),
                        path
                ));
    }

    @ExceptionHandler(NotExistentRoleException.class)
    private ResponseEntity<ErrorResponseDTO> notExistentRoleHandler (NotExistentRoleException exception, WebRequest request) {

        String path = request.getDescription(false).replaceAll("uri=", "");

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponseDTO(
                        Instant.now().toString(),
                        HttpStatus.INTERNAL_SERVER_ERROR.value(),
                        HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                        exception.getMessage(),
                        path
                ));
    }

    @ExceptionHandler(InvalidLoginException.class)
    private ResponseEntity<ErrorResponseDTO> invalidUserOrPassswordHandler (InvalidLoginException exception, WebRequest request) {

        String path = request.getDescription(false).replaceAll("uri=", "");

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponseDTO(
                        Instant.now().toString(),
                        HttpStatus.BAD_REQUEST.value(),
                        HttpStatus.BAD_REQUEST.toString(),
                        exception.getMessage(),
                        path
                ));
    }

    @ExceptionHandler(UsernameAlreadyUsedException.class)
    private ResponseEntity<ErrorResponseDTO> usernameAlreadyUsedHandler (UsernameAlreadyUsedException exception, WebRequest request) {
        String path = request.getDescription(false).replaceAll("uri=", "");

        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponseDTO(
                        Instant.now().toString(),
                        HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.toString(),
                        exception.getMessage(),
                        path
                ));
    }
}
