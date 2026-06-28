package heck.jokenponline.auth.internal.infra.handler;

import heck.jokenponline.auth.internal.infra.security.exceptions.InvalidTokenException;
import heck.jokenponline.auth.internal.infra.security.exceptions.NotExistentRoleException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTokenException.class)
    private ResponseEntity<ErrorResponseDTO> InvalidTokenHandler (InvalidTokenException exception, WebRequest request) {

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
    private ResponseEntity<ErrorResponseDTO> NotExistentRoleHandler (NotExistentRoleException exception, WebRequest request) {

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

    @ExceptionHandler(UsernameNotFoundException.class)
    private ResponseEntity<ErrorResponseDTO> NotExistentRoleHandler (UsernameNotFoundException exception, WebRequest request) {

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
}
