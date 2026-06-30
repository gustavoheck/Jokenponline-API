package heck.jokenponline.auth.internal.domain.exception;

public class CantCreateEmailValidationTokenException extends RuntimeException {
    public CantCreateEmailValidationTokenException(String message) {
        super(message);
    }
}
