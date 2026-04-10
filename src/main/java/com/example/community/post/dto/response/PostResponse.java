package com.example.community.post.dto.response;

import com.example.community.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponse {
    private Long postId;
    private String title;
    private String content;
    private Boolean isAnonymous;

    private Long boardId;
    private String boardName;

    private Long memberId;
    private String nickname;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public static PostResponse from(Post post) {
        return PostResponse.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .content(post.getContent())
                .isAnonymous(post.getIsAnonymous())
                .boardId(post.getBoard().getBoardId())
                .boardName(post.getBoard().getName())
                .memberId(post.getWriter().getMemberId())
                .nickname(post.getWriter().getNickname())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }
}
