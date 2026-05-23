package com.example.community.message.controller;

import com.example.community.message.dto.request.CreateMessageRequest;
import com.example.community.message.dto.response.CreateMessageResponse;
import com.example.community.message.dto.response.MessageListResponse;
import com.example.community.message.service.MessageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    // 쪽지 생성
    @PostMapping("/messages")
    public ResponseEntity<CreateMessageResponse> createMessage(@RequestHeader("Auth-Id") Long memberId,
                                                               @Valid @RequestBody CreateMessageRequest request) {
        CreateMessageResponse response = messageService.createMessage(memberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 쪽지 조회
    @GetMapping("/message-rooms/{messageRoomId}/messages")
    public ResponseEntity<MessageListResponse> getMessages(@PathVariable Long messageRoomId,
                                                           @RequestHeader("Auth-Id") Long memberId) {
        MessageListResponse response = messageService.getMessages(messageRoomId, memberId);
        return ResponseEntity.ok(response);
    }
}
