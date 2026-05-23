package com.example.community.message.dto.response;

import com.example.community.message.domain.Message;
import com.example.community.messageroom.domain.MessageRoom;

import java.util.List;

public record MessageListResponse(
        Long messageRoomId,
        Long opponentId,
        List<MessageItemResponse> messages
) {
    public static MessageListResponse from(MessageRoom messageRoom, List<Message> messages, Long memberId) {
        List<MessageItemResponse> items = messages.stream()
                .map(message -> MessageItemResponse.from(message, memberId))
                .toList();

        return new MessageListResponse(
                messageRoom.getMessageRoomId(),
                messageRoom.getOpponent(memberId).getMemberId(),
                items
        );
    }
}
