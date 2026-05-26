package com.example.community.notification.dto.response;

import com.example.community.notification.domain.Notification;
import com.example.community.notification.domain.NotificationType;

import java.time.LocalDateTime;

public record NotificationItem(
        Long notificationId,
        NotificationType type,
        String boardName,
        String content,
        LocalDateTime createdAt
) {
    public static NotificationItem from(Notification notification) {
        return new NotificationItem(
                notification.getNotificationId(),
                notification.getType(),
                notification.getBoardName(),
                notification.getContent(),
                notification.getCreatedAt()
        );
    }
}
