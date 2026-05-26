package com.example.community.message.repository;

import com.example.community.message.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {

    // 쪽지방 내 모든 쪽지 오래된 순 조회
    List<Message> findAllByMessageRoomMessageRoomIdOrderByCreatedAtAsc(Long messageRoomId);

    // 쪽지방의 가장 최근 쪽지 1개 조회
    Optional<Message> findFirstByMessageRoomMessageRoomIdOrderByCreatedAtDesc(Long messageRoomId);
}
