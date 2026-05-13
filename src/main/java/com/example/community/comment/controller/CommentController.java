package com.example.community.comment.controller;

import com.example.community.comment.dto.request.CreateCommentRequest;
import com.example.community.comment.dto.request.UpdateCommentRequest;
import com.example.community.comment.dto.response.CommentListResponse;
import com.example.community.comment.dto.response.CommentResponse;
import com.example.community.comment.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    // 게시글별 댓글 목록 조회
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentListResponse> getCommentsByPost(@PathVariable Long postId) {
        CommentListResponse response = commentService.getCommentsByPost(postId);
        return ResponseEntity.ok(response);
    }

    // 작성자(회원)별 댓글 목록 조회
    @GetMapping("/members/{memberId}/comments")
    public ResponseEntity<CommentListResponse> getCommentsByMember(@PathVariable Long memberId) {
        CommentListResponse response = commentService.getCommentsByMember(memberId);
        return ResponseEntity.ok(response);
    }

    // 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentResponse> createComment(@PathVariable Long postId,
                                                        @RequestHeader("Auth-Id") Long authMemberId,
                                                        @Valid @RequestBody CreateCommentRequest request) {
        CommentResponse response = commentService.createComment(postId, authMemberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 댓글 수정
    @PatchMapping("/comments/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long commentId,
                                             @RequestHeader("Auth-Id") Long authMemberId,
                                             @Valid @RequestBody UpdateCommentRequest request) {
        commentService.updateComment(commentId, authMemberId, request);
        return ResponseEntity.noContent().build();
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId,
                                              @RequestHeader("Auth-Id") Long authMemberId) {
        commentService.deleteComment(commentId, authMemberId);
        return ResponseEntity.noContent().build();
    }
}
