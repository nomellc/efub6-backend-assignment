package com.example.community.notification.dto.response;

import java.util.List;

public record NotificationListResponse(
        List<NotificationItem> notifications,
        Long totalNotifications
) { }
