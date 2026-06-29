package heck.jokenponline.shared.infra.handler;

public record ErrorResponseDTO(
        String timestamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
