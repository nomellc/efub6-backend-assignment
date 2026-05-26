package com.example.community.messageroom.dto.response;

import java.util.List;

public record MessageRoomListResponse(
        List<MessageRoomSummary> messageRooms
) {
}
