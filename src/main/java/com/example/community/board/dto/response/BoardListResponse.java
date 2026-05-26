package com.example.community.board.dto.response;

import com.example.community.board.dto.summary.BoardSummary;

import java.util.List;

public record BoardListResponse(
        List<BoardSummary> boards,
        Long totalBoards
) { }
