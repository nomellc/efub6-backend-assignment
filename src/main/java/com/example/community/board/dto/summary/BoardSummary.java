package com.example.community.board.dto.summary;

import com.example.community.board.domain.Board;

public record BoardSummary(
        Long boardId,
        String name,
        String description
) {
    public static BoardSummary from(Board board) {
        return new BoardSummary(
                board.getBoardId(),
                board.getName(),
                board.getDescription()
        );
    }
}
