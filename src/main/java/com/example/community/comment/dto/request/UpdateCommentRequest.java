package com.example.community.comment.dto.request;

import jakarta.validation.constraints.Size;

public record UpdateCommentRequest(
        @Size(min = 1, max = 500, message = "댓글은 1자 이상 500자 이하여야 합니다.")
        String content
) { }
