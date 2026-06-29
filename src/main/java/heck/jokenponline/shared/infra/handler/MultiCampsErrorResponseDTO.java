package heck.jokenponline.shared.infra.handler;

import java.util.List;

public record MultiCampsErrorResponseDTO(
        String timestamp,
        Integer status,
        String error,
        List<ErrorCamp> message,
        String path
) {
}
