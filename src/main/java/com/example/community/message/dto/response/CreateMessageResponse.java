package com.example.community.message.dto.response;

import com.example.community.message.domain.Message;

import java.time.LocalDateTime;

public record CreateMessageResponse(
        Long messageRoomId,
        Long senderId,
        String content,
        LocalDateTime createdAt
) {
    public static CreateMessageResponse from(Message message) {
        return new CreateMessageResponse(
                message.getMessageRoom().getMessageRoomId(),
                message.getSender().getMemberId(),
                message.getContent(),
                message.getCreatedAt()
        );
    }
}
