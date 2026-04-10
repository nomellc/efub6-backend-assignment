package com.example.community.post.controller;

import com.example.community.post.dto.request.CreatePostRequest;
import com.example.community.post.dto.response.PostListResponse;
import com.example.community.post.dto.response.PostResponse;
import com.example.community.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boards/{boardId}/posts")
@RequiredArgsConstructor
public class BoardPostController {

    private final PostService postService;

    // 글 생성
    @PostMapping
    public ResponseEntity<PostResponse> createPost(@PathVariable Long boardId,
                                                   @RequestHeader("Auth-Id") Long memberId,
                                                   @Valid @RequestBody CreatePostRequest request) {
        PostResponse response = postService.createPost(boardId, memberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 글 목록조회
    @GetMapping
    public ResponseEntity<PostListResponse> getAllPosts(@PathVariable Long boardId) {
        PostListResponse response = postService.getAllPosts(boardId);
        return ResponseEntity.ok(response);
    }
}
