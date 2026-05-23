package com.example.community.notification.repository;

import com.example.community.notification.domain.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    // 특정 회원의 알림 목록 최신순 조회
    List<Notification> findAllByReceiverMemberIdOrderByCreatedAtDesc(Long memberId);
}
