package com.example.community.post.dto.response;

import com.example.community.post.dto.summary.PostSummary;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class PostListResponse {
    private List<PostSummary> posts;
    private Long totalPosts;
}
