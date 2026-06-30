package heck.jokenponline.auth.internal.app.exceptions;

public class InvalidEmailTokenException extends RuntimeException {
    public InvalidEmailTokenException(String message) {
        super(message);
    }
}
