package com.example.community.board.dto.request;

import jakarta.validation.constraints.NotNull;

public record UpdateBoardOwnerRequest(
        @NotNull(message = "newOwnerId는 필수 값입니다.")
        Long newOwnerId
) { }
