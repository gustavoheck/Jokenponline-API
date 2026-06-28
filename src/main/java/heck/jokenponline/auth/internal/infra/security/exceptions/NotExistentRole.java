package heck.jokenponline.auth.internal.infra.security.exceptions;

public class NotExistentRole extends RuntimeException {
    public NotExistentRole(String message) {
        super(message);
    }
}
