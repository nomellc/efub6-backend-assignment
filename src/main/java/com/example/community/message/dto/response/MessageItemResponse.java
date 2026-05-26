package com.example.community.message.dto.response;

import com.example.community.message.domain.Message;

import java.time.LocalDateTime;

public record MessageItemResponse(
        String content,
        LocalDateTime createdAt,
        boolean isSent
) {
    public static MessageItemResponse from(Message message, Long memberId) {
        boolean isSent = message.getSender().getMemberId().equals(memberId);
        return new MessageItemResponse(
                message.getContent(),
                message.getCreatedAt(),
                isSent
        );
    }
}
