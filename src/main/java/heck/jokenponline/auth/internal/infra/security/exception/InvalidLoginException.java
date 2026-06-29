package heck.jokenponline.auth.internal.infra.security.exception;

public class InvalidLoginException extends RuntimeException {
    public InvalidLoginException() {
        super("Username or password is invalid");
    }
}
