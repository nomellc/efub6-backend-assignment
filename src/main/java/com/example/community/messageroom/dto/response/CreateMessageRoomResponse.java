package com.example.community.messageroom.dto.response;

import com.example.community.message.domain.Message;
import com.example.community.messageroom.domain.MessageRoom;

import java.time.LocalDateTime;

public record CreateMessageRoomResponse(
        Long messageRoomId,
        Long senderId,
        Long receiverId,
        String content,
        LocalDateTime sentAt
) {
    public static CreateMessageRoomResponse from(MessageRoom messageRoom, Message message) {
        return new CreateMessageRoomResponse(
                messageRoom.getMessageRoomId(),
                messageRoom.getSender().getMemberId(),
                messageRoom.getReceiver().getMemberId(),
                message.getContent(),
                message.getCreatedAt()
        );
    }
}
