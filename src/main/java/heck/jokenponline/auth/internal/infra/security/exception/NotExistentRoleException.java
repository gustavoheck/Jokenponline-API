package heck.jokenponline.auth.internal.infra.security.exception;

public class NotExistentRoleException extends RuntimeException {
    public NotExistentRoleException(String message) {
        super(message);
    }
}
