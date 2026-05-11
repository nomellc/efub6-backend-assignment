package com.example.community.post.dto.response;

import com.example.community.post.dto.summary.PostSummary;

import java.util.List;

public record PostListResponse(
        List<PostSummary> posts,
        Long totalPosts
) { }
