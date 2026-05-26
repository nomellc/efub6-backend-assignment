package com.example.community.messageroom.controller;

import com.example.community.messageroom.dto.request.CreateMessageRoomRequest;
import com.example.community.messageroom.dto.response.CreateMessageRoomResponse;
import com.example.community.messageroom.dto.response.MessageRoomCheckResponse;
import com.example.community.messageroom.dto.response.MessageRoomListResponse;
import com.example.community.messageroom.service.MessageRoomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MessageRoomController {

    private final MessageRoomService messageRoomService;

    // 쪽지방 생성
    @PostMapping("/message-rooms")
    public ResponseEntity<CreateMessageRoomResponse> createMessageRoom(@RequestHeader("Auth-Id") Long memberId,
                                                                       @Valid @RequestBody CreateMessageRoomRequest request) {
        CreateMessageRoomResponse response = messageRoomService.createMessageRoom(memberId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 쪽지방 여부 조회
    @GetMapping("/message-rooms/check")
    public ResponseEntity<MessageRoomCheckResponse> checkMessageRoom(@RequestHeader("Auth-Id") Long memberId,
                                                                     @RequestParam Long receiverId,
                                                                     @RequestParam(required = false) Long postId) {
        MessageRoomCheckResponse response = messageRoomService.checkMessageRoom(memberId, receiverId, postId);
        return ResponseEntity.ok(response);
    }

    // 쪽지방 목록 조회
    @GetMapping("/message-rooms")
    public ResponseEntity<MessageRoomListResponse> getMessageRooms(@RequestHeader("Auth-Id") Long memberId) {
        MessageRoomListResponse response = messageRoomService.getMessageRooms(memberId);
        return ResponseEntity.ok(response);
    }

    // 쪽집방 삭제
    @DeleteMapping("/message-rooms/{messageRoomId}")
    public ResponseEntity<Void> deleteMessageRoom(@PathVariable Long messageRoomId,
                                                  @RequestHeader("Auth-Id") Long memberId) {
        messageRoomService.deleteMessageRoom(messageRoomId, memberId);
        return ResponseEntity.noContent().build();
    }
}
