package com.example.community.comment.service;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.community.comment.domain.Comment;
import com.example.community.comment.dto.request.CreateCommentRequest;
import com.example.community.comment.dto.request.UpdateCommentRequest;
import com.example.community.comment.dto.response.CommentListResponse;
import com.example.community.comment.dto.response.CommentResponse;
import com.example.community.comment.repository.CommentRepository;
import com.example.community.global.exception.CustomException;
import com.example.community.global.exception.ErrorCode;
import com.example.community.member.domain.Member;
import com.example.community.member.service.MemberService;
import com.example.community.post.domain.Post;
import com.example.community.post.service.PostService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostService postService;
    private final MemberService memberService;

    @Transactional
    public CommentResponse createComment(Long postId, Long memberId, CreateCommentRequest request) {
        Post post = postService.findByPostId(postId);
        Member writer = memberService.findByMemberId(memberId);

        Comment comment = Comment.builder()
                .post(post)
                .writer(writer)
                .content(request.content())
                .isAnonymous(request.isAnonymous())
                .build();

        Comment saved = commentRepository.save(comment);
        return CommentResponse.from(saved);
    }

    @Transactional
    public void updateComment(Long commentId, Long memberId, UpdateCommentRequest request) {
        Comment comment = findByCommentId(commentId);
        Member member = memberService.findByMemberId(memberId);

        authorizeCommentWriter(comment, member);
        comment.changeContent(request.content());
    }

    @Transactional(readOnly = true)
    public CommentListResponse getCommentsByPost(Long postId) {
        postService.findByPostId(postId);

        List<CommentResponse> comments = commentRepository.findAllByPost_IdOrderByCreatedAtDesc(postId).stream()
                .map(CommentResponse::from)
                .toList();

        return new CommentListResponse(comments, (long) comments.size());
    }

    @Transactional(readOnly = true)
    public CommentListResponse getCommentsByMember(Long memberId) {
        memberService.findByMemberId(memberId);

        List<CommentResponse> comments = commentRepository.findAllByWriter_MemberIdOrderByCreatedAtDesc(memberId).stream()
                .map(CommentResponse::from)
                .toList();

        return new CommentListResponse(comments, (long) comments.size());
    }

    private Comment findByCommentId(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));
    }

    private void authorizeCommentWriter(Comment comment, Member member) {
        if (!Objects.equals(comment.getWriter().getMemberId(), member.getMemberId())) {
            throw new CustomException(ErrorCode.COMMENT_ACCOUNT_MISMATCH);
        }
    }
}
