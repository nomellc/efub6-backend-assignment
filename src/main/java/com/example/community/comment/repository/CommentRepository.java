package com.example.community.comment.repository;

import com.example.community.comment.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByPost_IdOrderByCreatedAtDesc(Long postId);

    List<Comment> findAllByWriter_MemberIdOrderByCreatedAtDesc(Long memberId);
}
