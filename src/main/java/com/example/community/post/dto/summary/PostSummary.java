package com.example.community.post.dto.summary;

import com.example.community.post.domain.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostSummary {
    private Long postId;
    private Long boardId;
    private String nickname;
    private Boolean isAnonymous;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static PostSummary from(Post post) {
        return PostSummary.builder()
                .postId(post.getId())
                .boardId(post.getBoard().getBoardId())
                .nickname(post.getIsAnonymous() ? "익명" : post.getWriter().getNickname())
                .isAnonymous(post.getIsAnonymous())
                .title(post.getTitle())
                .content(post.getContent())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

}
