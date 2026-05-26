package com.example.community.post.repository;

import com.example.community.member.domain.Member;
import com.example.community.post.domain.Post;
import com.example.community.post.domain.PostLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PostLikeRepository extends JpaRepository<PostLike, Long> {

    boolean existsByPostAndMember(Post post, Member member);

    Optional<PostLike> findByPostAndMember(Post post, Member member);
}
