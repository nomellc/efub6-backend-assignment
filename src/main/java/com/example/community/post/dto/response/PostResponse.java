package com.example.community.post.dto.response;

import com.example.community.post.domain.Post;

import java.time.LocalDateTime;

public record PostResponse(
        Long postId,
        String title,
        String content,
        Boolean isAnonymous,
        Long boardId,
        String boardName,
        Long memberId,
        String nickname,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostResponse from(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getIsAnonymous(),
                post.getBoard().getBoardId(),
                post.getBoard().getName(),
                post.getWriter().getMemberId(),
                post.getWriter().getNickname(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
