package com.example.community.board.dto.response;

import com.example.community.board.domain.Board;
import com.example.community.member.domain.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class BoardResponse {
    private Long boardId;
    private String name;
    private String description;
    private String notice;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static BoardResponse from (Board board) {
        return BoardResponse.builder()
                .boardId(board.getBoardId())
                .name(board.getName())
                .description(board.getDescription())
                .notice(board.getNotice())
                .createdAt(board.getCreatedAt())
                .updatedAt(board.getUpdatedAt())
                .build();
    }
}
