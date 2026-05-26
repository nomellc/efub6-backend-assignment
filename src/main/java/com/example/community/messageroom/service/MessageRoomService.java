package com.example.community.messageroom.service;

import com.example.community.global.exception.CustomException;
import com.example.community.global.exception.ErrorCode;
import com.example.community.member.domain.Member;
import com.example.community.member.service.MemberService;
import com.example.community.message.domain.Message;
import com.example.community.message.repository.MessageRepository;
import com.example.community.messageroom.domain.MessageRoom;
import com.example.community.messageroom.dto.request.CreateMessageRoomRequest;
import com.example.community.messageroom.dto.response.CreateMessageRoomResponse;
import com.example.community.messageroom.dto.response.MessageRoomCheckResponse;
import com.example.community.messageroom.dto.response.MessageRoomListResponse;
import com.example.community.messageroom.dto.response.MessageRoomSummary;
import com.example.community.messageroom.repository.MessageRoomRepository;
import com.example.community.notification.service.NotificationService;
import com.example.community.post.domain.Post;
import com.example.community.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MessageRoomService {

    private final MessageRoomRepository messageRoomRepository;
    private final MessageRepository messageRepository;
    private final MemberService memberService;
    private final PostService postService;
    private final NotificationService notificationService;

    // 쪽지방 생성
    @Transactional
    public CreateMessageRoomResponse createMessageRoom(Long senderId, CreateMessageRoomRequest request) {
        if (senderId.equals(request.receiverId())) {
            throw new CustomException(ErrorCode.MESSAGE_ROOM_SELF_SEND);
        }

        Member sender = memberService.findByMemberId(senderId);
        Member receiver = memberService.findByMemberId(request.receiverId());

        if (request.postId() != null) {
            messageRoomRepository.findByPostAndParticipants(request.postId(), senderId, request.receiverId())
                    .ifPresent(room -> { throw new CustomException(ErrorCode.MESSAGE_ROOM_ALREADY_EXISTS); });
        } else {
            messageRoomRepository.findDirectRoomByParticipants(senderId, request.receiverId())
                    .ifPresent(room -> { throw new CustomException(ErrorCode.MESSAGE_ROOM_ALREADY_EXISTS); });
        }

        Post post = request.postId() != null ? postService.findByPostId(request.postId()) : null;

        MessageRoom messageRoom = MessageRoom.builder()
                .post(post)
                .sender(sender)
                .receiver(receiver)
                .build();
        MessageRoom savedRoom = messageRoomRepository.save(messageRoom);
        notificationService.createMessageRoomNotification(receiver);

        Message message = Message.builder()
                .messageRoom(savedRoom)
                .sender(sender)
                .content(request.content())
                .build();
        Message savedMessage = messageRepository.save(message);

        return CreateMessageRoomResponse.from(savedRoom, savedMessage);
    }

    // 두 회원 간 쪽지방 존재 여부 조회 (postId 없으면 direct room 조회)
    @Transactional(readOnly = true)
    public MessageRoomCheckResponse checkMessageRoom(Long memberId, Long receiverId, Long postId) {
        Optional<MessageRoom> messageRoom = postId != null
                ? messageRoomRepository.findByPostAndParticipants(postId, memberId, receiverId)
                : messageRoomRepository.findDirectRoomByParticipants(memberId, receiverId);

        return new MessageRoomCheckResponse(
                messageRoom.orElseThrow(() -> new CustomException(ErrorCode.MESSAGE_ROOM_NOT_FOUND)).getMessageRoomId()
        );
    }

    // 쪽지방 목록 조회 (최신 쪽지 기준 내림차순)
    @Transactional(readOnly = true)
    public MessageRoomListResponse getMessageRooms(Long memberId) {
        memberService.findByMemberId(memberId);

        List<MessageRoomSummary> summaries = messageRoomRepository
                .findAllByMemberIdWithDetails(memberId)
                .stream()
                .map(room -> {
                    Message latestMessage = messageRepository
                            .findFirstByMessageRoomMessageRoomIdOrderByCreatedAtDesc(room.getMessageRoomId())
                            .orElseThrow(() -> new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
                    return MessageRoomSummary.from(room, latestMessage, memberId);
                })
                .sorted(Comparator.comparing(MessageRoomSummary::lastSentAt).reversed())
                .toList();

        return new MessageRoomListResponse(summaries);
    }

    // 쪽지방 삭제
    @Transactional
    public void deleteMessageRoom(Long messageRoomId, Long memberId) {
        MessageRoom messageRoom = messageRoomRepository.findById(messageRoomId)
                .orElseThrow(() -> new CustomException(ErrorCode.MESSAGE_ROOM_NOT_FOUND));

        validateParticipant(messageRoom, memberId);
        messageRoomRepository.delete(messageRoom);
    }

    private void validateParticipant(MessageRoom messageRoom, Long memberId) {
        if (messageRoom.isNotParticipant(memberId)) {
            throw new CustomException(ErrorCode.MESSAGE_ROOM_ACCOUNT_MISMATCH);
        }
    }
}
