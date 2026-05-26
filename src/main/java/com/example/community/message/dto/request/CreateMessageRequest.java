package com.example.community.message.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMessageRequest(
        @NotNull Long messageRoomId,
        @NotBlank String content
) {
}
