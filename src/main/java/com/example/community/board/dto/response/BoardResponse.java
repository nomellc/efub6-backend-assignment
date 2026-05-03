package com.example.community.board.dto.response;

import com.example.community.board.domain.Board;

import java.time.LocalDateTime;

public record BoardResponse(
        Long boardId,
        String name,
        String description,
        String notice,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static BoardResponse from(Board board) {
        return new BoardResponse(
                board.getBoardId(),
                board.getName(),
                board.getDescription(),
                board.getNotice(),
                board.getCreatedAt(),
                board.getUpdatedAt()
        );
    }
}
