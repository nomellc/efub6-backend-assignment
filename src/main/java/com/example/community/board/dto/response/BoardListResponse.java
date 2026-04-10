package com.example.community.board.dto.response;

import com.example.community.board.dto.summary.BoardSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class BoardListResponse {
    private List<BoardSummary> boards;
    private Long totalBoards;
}
