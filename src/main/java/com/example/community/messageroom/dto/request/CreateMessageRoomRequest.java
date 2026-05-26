package com.example.community.messageroom.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateMessageRoomRequest(
        @NotNull Long receiverId,
        @NotBlank String content,
        Long postId
) {
}
