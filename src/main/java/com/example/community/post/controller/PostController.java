package com.example.community.post.controller;

import com.example.community.post.dto.request.UpdatePostRequest;
import com.example.community.post.dto.response.PostResponse;
import com.example.community.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 글 상세 조회
    @GetMapping("/{postId}")
    public ResponseEntity<PostResponse> getPost(@PathVariable Long postId) {
        PostResponse response = postService.getPost(postId);
        return ResponseEntity.ok(response);
    }

    // 글 수정
    @PatchMapping("/{postId}")
    public ResponseEntity<Void> updatePostContent(@PathVariable Long postId,
                                                  @RequestHeader("Auth-Id") Long accountId,
                                                  @Valid @RequestBody UpdatePostRequest request) {
        postService.updatePostContent(postId, accountId, request);
        return ResponseEntity.noContent().build();
    }

    // 글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost (@PathVariable Long postId,
                                            @RequestHeader("Auth-Id") Long accountId) {
        postService.deletePost(postId, accountId);
        return ResponseEntity.noContent().build();
    }

}
