package heck.jokenponline.auth.internal.infra.security.exceptions;

public class NotExistentRoleException extends RuntimeException {
    public NotExistentRoleException(String message) {
        super(message);
    }
}
