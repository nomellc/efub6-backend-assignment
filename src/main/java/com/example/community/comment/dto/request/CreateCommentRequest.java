package com.example.community.comment.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateCommentRequest(
        @NotNull Boolean isAnonymous,

        @NotBlank(message = "댓글 내용을 입력해야 합니다.")
        @Size(max = 500, message = "댓글은 500자 이하여야 합니다.")
        String content
) { }
