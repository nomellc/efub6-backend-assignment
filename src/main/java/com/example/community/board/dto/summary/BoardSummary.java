package com.example.community.board.dto.summary;

import com.example.community.board.domain.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BoardSummary {
    private Long boardId;
    private String name;
    private String description;

    public static BoardSummary from (Board board) {
        return BoardSummary.builder()
                .boardId(board.getBoardId())
                .name(board.getName())
                .description(board.getDescription())
                .build();
    }
}
